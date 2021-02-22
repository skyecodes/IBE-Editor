package com.github.franckyi.ibeeditor.impl.client.mvc.model;

import com.github.franckyi.databindings.ObservableListFactory;
import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.gamehooks.util.common.tag.*;
import com.github.franckyi.ibeeditor.api.client.mvc.model.TagModel;

import java.util.stream.Collectors;

public class TagModelImpl implements TagModel {
    private final ObservableList<TagModel> children = ObservableListFactory.arrayList();
    private final BooleanProperty expandedProperty = PropertyFactory.ofBoolean();
    private final ObjectProperty<TagModel> parentProperty;
    private final BooleanProperty childrenChangedProperty = PropertyFactory.ofBoolean();
    private final StringProperty nameProperty;
    private final StringProperty valueProperty;
    protected final Tag<?> tag;
    protected byte forcedTagType;

    public TagModelImpl(Tag<?> tag) {
        this(tag, null, null, null);
        setExpanded(true);
    }

    public TagModelImpl(byte forcedTagType, TagModel parent, String value) {
        this(null, parent, null, value);
        this.forcedTagType = forcedTagType;
    }

    public TagModelImpl(Tag<?> tag, TagModel parent, String name, String value) {
        this.tag = tag;
        parentProperty = PropertyFactory.ofObject(parent);
        nameProperty = PropertyFactory.ofString(name);
        valueProperty = PropertyFactory.ofString(value);
        if (tag != null) {
            switch (tag.getType()) {
                case Tag.COMPOUND_ID:
                    children.setAll(((ObjectTag) tag).getValue().entrySet()
                            .stream()
                            .map(entry -> new TagModelImpl(entry.getValue(), this, entry.getKey(), null))
                            .collect(Collectors.toList())
                    );
                    break;
                case Tag.LIST_ID:
                    children.setAll(((ArrayTag) tag).getValue()
                            .stream()
                            .map(tag1 -> new TagModelImpl(tag1, this, null, null))
                            .collect(Collectors.toList())
                    );
                    break;
                case Tag.BYTE_ARRAY_ID:
                    children.setAll(((ByteArrayTag) tag).getValue()
                            .stream()
                            .map(b -> new TagModelImpl(Tag.BYTE_ID, this, Byte.toString(b)))
                            .collect(Collectors.toList())
                    );
                    break;
                case Tag.INT_ARRAY_ID:
                    children.setAll(((IntArrayTag) tag).getValue()
                            .stream()
                            .map(i -> new TagModelImpl(Tag.INT_ID, this, Integer.toString(i)))
                            .collect(Collectors.toList())
                    );
                    break;
                case Tag.LONG_ARRAY_ID:
                    children.setAll(((LongArrayTag) tag).getValue()
                            .stream()
                            .map(l -> new TagModelImpl(Tag.LONG_ID, this, Long.toString(l)))
                            .collect(Collectors.toList())
                    );
                    break;
                default:
                    setValue(tag.getValue().toString());
            }
        }
        getChildren().addListener(this::notifyChange);
    }

    @Override
    public ObservableList<TagModel> getChildren() {
        return children;
    }

    @Override
    public BooleanProperty expandedProperty() {
        return expandedProperty;
    }

    @Override
    public ObjectProperty<TagModel> parentProperty() {
        return parentProperty;
    }

    @Override
    public BooleanProperty childrenChangedProperty() {
        return childrenChangedProperty;
    }

    @Override
    public StringProperty nameProperty() {
        return nameProperty;
    }

    @Override
    public StringProperty valueProperty() {
        return valueProperty;
    }

    @Override
    public byte getTagType() {
        return tag != null ? tag.getType() : forcedTagType;
    }

    @Override
    public Tag<?> build() {
        if (tag != null) {
            switch (tag.getType()) {
                case Tag.BYTE_ID:
                    return new ByteTag(Byte.parseByte(getValue()));
                case Tag.SHORT_ID:
                    return new ShortTag(Short.parseShort(getValue()));
                case Tag.INT_ID:
                    return new IntTag(Integer.parseInt(getValue()));
                case Tag.LONG_ID:
                    return new LongTag(Long.parseLong(getValue()));
                case Tag.FLOAT_ID:
                    return new FloatTag(Float.parseFloat(getValue()));
                case Tag.DOUBLE_ID:
                    return new DoubleTag(Double.parseDouble(getValue()));
                case Tag.BYTE_ARRAY_ID:
                    return new ByteArrayTag(getChildren()
                            .stream()
                            .map(TagModel::getValue)
                            .map(Byte::parseByte)
                            .collect(Collectors.toList())
                    );
                case Tag.STRING_ID:
                    return new StringTag(getValue());
                case Tag.LIST_ID:
                    return new ArrayTag(getChildren().stream().map(TagModel::build).collect(Collectors.toList()));
                case Tag.COMPOUND_ID:
                    return new ObjectTag(getChildren()
                            .stream()
                            .collect(Collectors.toMap(TagModel::getName, TagModel::build))
                    );
                case Tag.INT_ARRAY_ID:
                    return new IntArrayTag(getChildren()
                            .stream()
                            .map(TagModel::getValue)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())
                    );
                case Tag.LONG_ARRAY_ID:
                    return new LongArrayTag(getChildren()
                            .stream()
                            .map(TagModel::getValue)
                            .map(Long::parseLong)
                            .collect(Collectors.toList())
                    );
            }
        }
        return null;
    }
}
