package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.guapi.api.mvc.Model;
import com.github.franckyi.guapi.api.node.TreeView;
import com.github.franckyi.ibeeditor.mixin.CompoundTagMixin;
import net.minecraft.nbt.*;
import org.apache.commons.lang3.ArrayUtils;

import java.util.stream.Stream;

public class NBTTagModel implements TreeView.TreeItem<NBTTagModel>, Model {
    private final ObservableList<NBTTagModel> children = ObservableList.create();
    private final BooleanProperty expandedProperty = BooleanProperty.create();
    private final ObjectProperty<NBTTagModel> parentProperty;
    private final BooleanProperty childrenChangedProperty = BooleanProperty.create();
    private final StringProperty nameProperty;
    private final StringProperty valueProperty;
    private final BooleanProperty validProperty = BooleanProperty.create();
    protected final Tag tag;
    protected byte forcedTagType;

    public NBTTagModel(Tag tag) {
        this(tag, null, null, null);
        setExpanded(true);
    }

    public NBTTagModel(byte forcedTagType, NBTTagModel parent, String value) {
        this(null, parent, null, value);
        this.forcedTagType = forcedTagType;
    }

    public NBTTagModel(Tag tag, NBTTagModel parent, String name, String value) {
        this.tag = tag;
        parentProperty = ObjectProperty.create(parent);
        nameProperty = StringProperty.create(name);
        valueProperty = StringProperty.create(value);
        if (tag != null) {
            switch (tag.getId()) {
                case Tag.TAG_COMPOUND -> children.setAll(((CompoundTagMixin) tag).getTags().entrySet()
                        .stream()
                        .map(entry -> new NBTTagModel(entry.getValue(), this, entry.getKey(), null))
                        .toList()
                );
                case Tag.TAG_LIST -> children.setAll(((ListTag) tag)
                        .stream()
                        .map(tag1 -> new NBTTagModel(tag1, this, null, null))
                        .toList()
                );
                case Tag.TAG_BYTE_ARRAY -> children.setAll(Stream.of(ArrayUtils.toObject(((ByteArrayTag) tag).getAsByteArray()))
                        .map(b -> new NBTTagModel(Tag.TAG_BYTE, this, Byte.toString(b)))
                        .toList()
                );
                case Tag.TAG_INT_ARRAY -> children.setAll(Stream.of(ArrayUtils.toObject(((IntArrayTag) tag).getAsIntArray()))
                        .map(i -> new NBTTagModel(Tag.TAG_INT, this, Integer.toString(i)))
                        .toList()
                );
                case Tag.TAG_LONG_ARRAY -> children.setAll(Stream.of(ArrayUtils.toObject(((LongArrayTag) tag).getAsLongArray()))
                        .map(l -> new NBTTagModel(Tag.TAG_LONG, this, Long.toString(l)))
                        .toList()
                );
                case Tag.TAG_BYTE -> setValue(Byte.toString(((ByteTag) tag).getAsByte()));
                case Tag.TAG_SHORT -> setValue(Short.toString(((ShortTag) tag).getAsShort()));
                case Tag.TAG_INT -> setValue(Integer.toString(((IntTag) tag).getAsInt()));
                case Tag.TAG_LONG -> setValue(Long.toString(((LongTag) tag).getAsLong()));
                case Tag.TAG_FLOAT -> setValue(Float.toString(((FloatTag) tag).getAsFloat()));
                case Tag.TAG_DOUBLE -> setValue(Double.toString(((DoubleTag) tag).getAsDouble()));
                default -> setValue(tag.getAsString());
            }
        }
        getChildren().addListener(() -> this.getRoot().setChildrenChanged(true));
        validProperty().addListener(() -> this.getRoot().updateValidity());
    }

    @Override
    public ObservableList<NBTTagModel> getChildren() {
        return children;
    }

    @Override
    public BooleanProperty expandedProperty() {
        return expandedProperty;
    }

    @Override
    public ObjectProperty<NBTTagModel> parentProperty() {
        return parentProperty;
    }

    @Override
    public BooleanProperty childrenChangedProperty() {
        return childrenChangedProperty;
    }

    public String getName() {
        return nameProperty().getValue();
    }

    public StringProperty nameProperty() {
        return nameProperty;
    }

    public void setName(String value) {
        nameProperty().setValue(value);
    }

    public String getValue() {
        return valueProperty().getValue();
    }

    public StringProperty valueProperty() {
        return valueProperty;
    }

    public void setValue(String value) {
        valueProperty().setValue(value);
    }

    public boolean isValid() {
        return validProperty().getValue();
    }

    public BooleanProperty validProperty() {
        return validProperty;
    }

    public void setValid(boolean value) {
        validProperty().setValue(value);
    }

    public byte getTagType() {
        return tag != null ? tag.getId() : forcedTagType;
    }

    public boolean canBuild() {
        return tag != null;
    }

    public Tag build() {
        if (canBuild()) {
            switch (tag.getId()) {
                case Tag.TAG_BYTE:
                    return ByteTag.valueOf(Byte.parseByte(getValue()));
                case Tag.TAG_SHORT:
                    return ShortTag.valueOf(Short.parseShort(getValue()));
                case Tag.TAG_INT:
                    return IntTag.valueOf(Integer.parseInt(getValue()));
                case Tag.TAG_LONG:
                    return LongTag.valueOf(Long.parseLong(getValue()));
                case Tag.TAG_FLOAT:
                    return FloatTag.valueOf(Float.parseFloat(getValue()));
                case Tag.TAG_DOUBLE:
                    return DoubleTag.valueOf(Double.parseDouble(getValue()));
                case Tag.TAG_BYTE_ARRAY:
                    return new ByteArrayTag(getChildren()
                            .stream()
                            .map(NBTTagModel::getValue)
                            .map(Byte::parseByte)
                            .toList()
                    );
                case Tag.TAG_STRING:
                    return StringTag.valueOf(getValue());
                case Tag.TAG_LIST:
                    ListTag listTag = new ListTag();
                    listTag.addAll(getChildren()
                            .stream()
                            .map(NBTTagModel::build)
                            .toList());
                    return listTag;
                case Tag.TAG_COMPOUND:
                    CompoundTag compoundTag = new CompoundTag();
                    getChildren().forEach(childTag -> compoundTag.put(childTag.getName(), childTag.build()));
                    return compoundTag;
                case Tag.TAG_INT_ARRAY:
                    return new IntArrayTag(getChildren()
                            .stream()
                            .map(NBTTagModel::getValue)
                            .map(Integer::parseInt)
                            .toList()
                    );
                case Tag.TAG_LONG_ARRAY:
                    return new LongArrayTag(getChildren()
                            .stream()
                            .map(NBTTagModel::getValue)
                            .map(Long::parseLong)
                            .toList()
                    );
            }
        }
        return null;
    }

    public void updateValidity() {
        validProperty().unbind();
        setValid(getChildren().stream().allMatch(NBTTagModel::isValid));
    }

    public NBTTagModel createClipboardTag() {
        return canBuild()
                ? new NBTTagModel(build(), null, getName(), getValue())
                : new NBTTagModel(getTagType(), null, getValue());
    }
}
