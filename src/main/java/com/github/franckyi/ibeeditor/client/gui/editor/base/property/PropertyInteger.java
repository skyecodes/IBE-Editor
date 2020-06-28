package com.github.franckyi.ibeeditor.client.gui.editor.base.property;

import com.github.franckyi.guapi.node.IntegerField;
import com.github.franckyi.guapi.node.NumberField;

import java.util.function.Consumer;

public class PropertyInteger extends PropertyNumber<Integer> {

    public PropertyInteger(String name, int initialValue, Consumer<Integer> action) {
        super(name, initialValue, action);
    }

    public PropertyInteger(String name, int initialValue, Consumer<Integer> action, int labelSize) {
        super(name, initialValue, action, labelSize);
    }

    public PropertyInteger(String name, int initialValue, Consumer<Integer> action, int min, int max) {
        super(name, initialValue, action, min, max);
    }

    @Override
    protected NumberField<Integer> createField(Integer value) {
        return new IntegerField(value);
    }
}
