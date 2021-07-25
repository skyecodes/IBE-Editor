package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.tag.*;
import com.github.franckyi.guapi.api.mvc.Model;
import com.github.franckyi.guapi.api.node.TreeView;
import org.apache.commons.lang3.ArrayUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NBTTagModel implements TreeView.TreeItem<NBTTagModel>, Model {
    private final ObservableList<NBTTagModel> children = DataBindings.getObservableListFactory().createObservableArrayList();
    private final BooleanProperty expandedProperty = DataBindings.getPropertyFactory().createBooleanProperty();
    private final ObjectProperty<NBTTagModel> parentProperty;
    private final BooleanProperty childrenChangedProperty = DataBindings.getPropertyFactory().createBooleanProperty();
    private final StringProperty nameProperty;
    private final StringProperty valueProperty;
    private final BooleanProperty validProperty = DataBindings.getPropertyFactory().createBooleanProperty();
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
        parentProperty = DataBindings.getPropertyFactory().createObjectProperty(parent);
        nameProperty = DataBindings.getPropertyFactory().createStringProperty(name);
        valueProperty = DataBindings.getPropertyFactory().createStringProperty(value);
        if (tag != null) {
            switch (tag.getType()) {
                case Tag.COMPOUND_ID:
                    children.setAll(((CompoundTag) tag).entrySet()
                            .stream()
                            .map(entry -> new NBTTagModel(entry.getValue(), this, entry.getKey(), null))
                            .collect(Collectors.toList())
                    );
                    break;
                case Tag.LIST_ID:
                    children.setAll(((ListTag) tag)
                            .stream()
                            .map(tag1 -> new NBTTagModel(tag1, this, null, null))
                            .collect(Collectors.toList())
                    );
                    break;
                case Tag.BYTE_ARRAY_ID:
                    children.setAll(Stream.of(ArrayUtils.toObject(((ByteArrayTag) tag).getValue()))
                            .map(b -> new NBTTagModel(Tag.BYTE_ID, this, Byte.toString(b)))
                            .collect(Collectors.toList())
                    );
                    break;
                case Tag.INT_ARRAY_ID:
                    children.setAll(Stream.of(ArrayUtils.toObject(((IntArrayTag) tag).getValue()))
                            .map(i -> new NBTTagModel(Tag.INT_ID, this, Integer.toString(i)))
                            .collect(Collectors.toList())
                    );
                    break;
                case Tag.LONG_ARRAY_ID:
                    children.setAll(Stream.of(ArrayUtils.toObject(((LongArrayTag) tag).getValue()))
                            .map(l -> new NBTTagModel(Tag.LONG_ID, this, Long.toString(l)))
                            .collect(Collectors.toList())
                    );
                    break;
                default:
                    setValue(tag.toString());
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
        return tag != null ? tag.getType() : forcedTagType;
    }

    public boolean canBuild() {
        return tag != null;
    }

    public Tag build() {
        if (canBuild()) {
            switch (tag.getType()) {
                case Tag.BYTE_ID:
                    return Game.getCommon().getTagFactory().createByteTag(Byte.parseByte(getValue()));
                case Tag.SHORT_ID:
                    return Game.getCommon().getTagFactory().createShortTag(Short.parseShort(getValue()));
                case Tag.INT_ID:
                    return Game.getCommon().getTagFactory().createIntTag(Integer.parseInt(getValue()));
                case Tag.LONG_ID:
                    return Game.getCommon().getTagFactory().createLongTag(Long.parseLong(getValue()));
                case Tag.FLOAT_ID:
                    return Game.getCommon().getTagFactory().createFloatTag(Float.parseFloat(getValue()));
                case Tag.DOUBLE_ID:
                    return Game.getCommon().getTagFactory().createDoubleTag(Double.parseDouble(getValue()));
                case Tag.BYTE_ARRAY_ID:
                    return Game.getCommon().getTagFactory().createByteArrayTag(getChildren()
                            .stream()
                            .map(NBTTagModel::getValue)
                            .map(Byte::parseByte)
                            .collect(Collectors.toList())
                    );
                case Tag.STRING_ID:
                    return Game.getCommon().getTagFactory().createStringTag(getValue());
                case Tag.LIST_ID:
                    return Game.getCommon().getTagFactory().createListTag(getChildren()
                            .stream()
                            .map(NBTTagModel::build)
                            .collect(Collectors.toList())
                    );
                case Tag.COMPOUND_ID:
                    return Game.getCommon().getTagFactory().createCompoundTag(getChildren()
                            .stream()
                            .collect(Collectors.toMap(NBTTagModel::getName, NBTTagModel::build))
                    );
                case Tag.INT_ARRAY_ID:
                    return Game.getCommon().getTagFactory().createIntArrayTag(getChildren()
                            .stream()
                            .map(NBTTagModel::getValue)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())
                    );
                case Tag.LONG_ARRAY_ID:
                    return Game.getCommon().getTagFactory().createLongArrayTag(getChildren()
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