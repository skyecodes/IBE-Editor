package com.github.franckyi.ibeeditor.client.gui.editor.base.property;

import com.github.franckyi.guapi.node.ShortField;
import com.github.franckyi.guapi.node.NumberField;

import java.util.function.Consumer;

public class PropertyShort extends PropertyNumber<Short> {

    public PropertyShort(String name, short initialValue, Consumer<Short> action) {
        super(name, initialValue, action);
    }

    public PropertyShort(String name, short initialValue, Consumer<Short> action, int labelSize) {
        super(name, initialValue, action, labelSize);
    }

    public PropertyShort(String name, short initialValue, Consumer<Short> action, short min, short max) {
        super(name, initialValue, action, min, max);
    }

    @Override
    protected NumberField<Short> createField(Short value) {
        return new ShortField(value);
    }
}
