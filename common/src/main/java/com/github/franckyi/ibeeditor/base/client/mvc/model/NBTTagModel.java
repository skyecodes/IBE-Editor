package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.guapi.api.mvc.Model;
import com.github.franckyi.guapi.api.node.TreeView;
import com.github.franckyi.ibeeditor.base.common.TagHelper;
import com.github.franckyi.ibeeditor.mixin.CompoundTagMixin;
import net.minecraft.nbt.*;
import org.apache.commons.lang3.ArrayUtils;

import java.util.stream.Collectors;
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
                case TagHelper.COMPOUND_ID:
                    children.setAll(((CompoundTagMixin) tag).getTags().entrySet()
                            .stream()
                            .map(entry -> new NBTTagModel(entry.getValue(), this, entry.getKey(), null))
                            .collect(Collectors.toList())
                    );
                    break;
                case TagHelper.LIST_ID:
                    children.setAll(((ListTag) tag)
                            .stream()
                            .map(tag1 -> new NBTTagModel(tag1, this, null, null))
                            .collect(Collectors.toList())
                    );
                    break;
                case TagHelper.BYTE_ARRAY_ID:
                    children.setAll(Stream.of(ArrayUtils.toObject(((ByteArrayTag) tag).getAsByteArray()))
                            .map(b -> new NBTTagModel(TagHelper.BYTE_ID, this, Byte.toString(b)))
                            .collect(Collectors.toList())
                    );
                    break;
                case TagHelper.INT_ARRAY_ID:
                    children.setAll(Stream.of(ArrayUtils.toObject(((IntArrayTag) tag).getAsIntArray()))
                            .map(i -> new NBTTagModel(TagHelper.INT_ID, this, Integer.toString(i)))
                            .collect(Collectors.toList())
                    );
                    break;
                case TagHelper.LONG_ARRAY_ID:
                    children.setAll(Stream.of(ArrayUtils.toObject(((LongArrayTag) tag).getAsLongArray()))
                            .map(l -> new NBTTagModel(TagHelper.LONG_ID, this, Long.toString(l)))
                            .collect(Collectors.toList())
                    );
                    break;
                case TagHelper.BYTE_ID:
                    setValue(Byte.toString(((ByteTag) tag).getAsByte()));
                    break;
                case TagHelper.SHORT_ID:
                    setValue(Short.toString(((ShortTag) tag).getAsShort()));
                    break;
                case TagHelper.INT_ID:
                    setValue(Integer.toString(((IntTag) tag).getAsInt()));
                    break;
                case TagHelper.LONG_ID:
                    setValue(Long.toString(((LongTag) tag).getAsLong()));
                    break;
                case TagHelper.FLOAT_ID:
                    setValue(Float.toString(((FloatTag) tag).getAsFloat()));
                    break;
                case TagHelper.DOUBLE_ID:
                    setValue(Double.toString(((DoubleTag) tag).getAsDouble()));
                    break;
                default:
                    setValue(tag.getAsString());
                    break;
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
                case TagHelper.BYTE_ID:
                    return ByteTag.valueOf(Byte.parseByte(getValue()));
                case TagHelper.SHORT_ID:
                    return ShortTag.valueOf(Short.parseShort(getValue()));
                case TagHelper.INT_ID:
                    return IntTag.valueOf(Integer.parseInt(getValue()));
                case TagHelper.LONG_ID:
                    return LongTag.valueOf(Long.parseLong(getValue()));
                case TagHelper.FLOAT_ID:
                    return FloatTag.valueOf(Float.parseFloat(getValue()));
                case TagHelper.DOUBLE_ID:
                    return DoubleTag.valueOf(Double.parseDouble(getValue()));
                case TagHelper.BYTE_ARRAY_ID:
                    return new ByteArrayTag(getChildren()
                            .stream()
                            .map(NBTTagModel::getValue)
                            .map(Byte::parseByte)
                            .collect(Collectors.toList())
                    );
                case TagHelper.STRING_ID:
                    return StringTag.valueOf(getValue());
                case TagHelper.LIST_ID:
                    ListTag listTag = new ListTag();
                    listTag.addAll(getChildren()
                            .stream()
                            .map(NBTTagModel::build)
                            .collect(Collectors.toList()));
                    return listTag;
                case TagHelper.COMPOUND_ID:
                    CompoundTag compoundTag = new CompoundTag();
                    getChildren().forEach(childTag -> compoundTag.put(childTag.getName(), childTag.build()));
                    return compoundTag;
                case TagHelper.INT_ARRAY_ID:
                    return new IntArrayTag(getChildren()
                            .stream()
                            .map(NBTTagModel::getValue)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())
                    );
                case TagHelper.LONG_ARRAY_ID:
                    return new LongArrayTag(getChildren()
                            .stream()
                            .map(NBTTagModel::getValue)
                            .map(Long::parseLong)
                            .collect(Collectors.toList())
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
