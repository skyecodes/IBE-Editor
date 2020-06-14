package com.github.franckyi.ibeeditor.client.gui.editor.base.property;

import com.github.franckyi.guapi.node.NumberField;
import com.github.franckyi.guapi.node.LongField;

import java.util.function.Consumer;

public class PropertyLong extends PropertyNumber<Long> {

    public PropertyLong(String name, long initialValue, Consumer<Long> action) {
        super(name, initialValue, action);
    }

    public PropertyLong(String name, long initialValue, Consumer<Long> action, int labelSize) {
        super(name, initialValue, action, labelSize);
    }

    public PropertyLong(String name, long initialValue, Consumer<Long> action, long min, long max) {
        super(name, initialValue, action, min, max);
    }

    @Override
    protected NumberField<Long> createField(Long value) {
        return new LongField(value);
    }
}
