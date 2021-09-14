package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.gameadapter.api.common.tag.*;
import com.github.franckyi.guapi.api.mvc.Model;
import com.github.franckyi.guapi.api.node.TreeView;
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
    protected final ITag tag;
    protected byte forcedTagType;

    public NBTTagModel(ITag tag) {
        this(tag, null, null, null);
        setExpanded(true);
    }

    public NBTTagModel(byte forcedTagType, NBTTagModel parent, String value) {
        this(null, parent, null, value);
        this.forcedTagType = forcedTagType;
    }

    public NBTTagModel(ITag tag, NBTTagModel parent, String name, String value) {
        this.tag = tag;
        parentProperty = ObjectProperty.create(parent);
        nameProperty = StringProperty.create(name);
        valueProperty = StringProperty.create(value);
        if (tag != null) {
            switch (tag.getType()) {
                case ITag.COMPOUND_ID -> children.setAll(((ICompoundTag) tag).entrySet()
                        .stream()
                        .map(entry -> new NBTTagModel(entry.getValue(), this, entry.getKey(), null))
                        .collect(Collectors.toList())
                );
                case ITag.LIST_ID -> children.setAll(((IListTag) tag)
                        .stream()
                        .map(tag1 -> new NBTTagModel(tag1, this, null, null))
                        .collect(Collectors.toList())
                );
                case ITag.BYTE_ARRAY_ID -> children.setAll(Stream.of(ArrayUtils.toObject(((IByteArrayTag) tag).getValue()))
                        .map(b -> new NBTTagModel(ITag.BYTE_ID, this, Byte.toString(b)))
                        .collect(Collectors.toList())
                );
                case ITag.INT_ARRAY_ID -> children.setAll(Stream.of(ArrayUtils.toObject(((IIntArrayTag) tag).getValue()))
                        .map(i -> new NBTTagModel(ITag.INT_ID, this, Integer.toString(i)))
                        .collect(Collectors.toList())
                );
                case ITag.LONG_ARRAY_ID -> children.setAll(Stream.of(ArrayUtils.toObject(((ILongArrayTag) tag).getValue()))
                        .map(l -> new NBTTagModel(ITag.LONG_ID, this, Long.toString(l)))
                        .collect(Collectors.toList())
                );
                default -> setValue(tag.getStringValue());
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

    public ITag build() {
        if (canBuild()) {
            switch (tag.getType()) {
                case ITag.BYTE_ID:
                    return IByteTag.create(Byte.parseByte(getValue()));
                case ITag.SHORT_ID:
                    return IShortTag.create(Short.parseShort(getValue()));
                case ITag.INT_ID:
                    return IIntTag.create(Integer.parseInt(getValue()));
                case ITag.LONG_ID:
                    return ILongTag.create(Long.parseLong(getValue()));
                case ITag.FLOAT_ID:
                    return IFloatTag.create(Float.parseFloat(getValue()));
                case ITag.DOUBLE_ID:
                    return IDoubleTag.create(Double.parseDouble(getValue()));
                case ITag.BYTE_ARRAY_ID:
                    return IByteArrayTag.create(getChildren()
                            .stream()
                            .map(NBTTagModel::getValue)
                            .map(Byte::parseByte)
                            .collect(Collectors.toList())
                    );
                case ITag.STRING_ID:
                    return IStringTag.create(getValue());
                case ITag.LIST_ID:
                    return IListTag.create(getChildren()
                            .stream()
                            .map(NBTTagModel::build)
                            .collect(Collectors.toList())
                    );
                case ITag.COMPOUND_ID:
                    return ICompoundTag.create(getChildren()
                            .stream()
                            .collect(Collectors.toMap(NBTTagModel::getName, NBTTagModel::build))
                    );
                case ITag.INT_ARRAY_ID:
                    return IIntArrayTag.create(getChildren()
                            .stream()
                            .map(NBTTagModel::getValue)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())
                    );
                case ITag.LONG_ARRAY_ID:
                    return ILongArrayTag.create(getChildren()
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
