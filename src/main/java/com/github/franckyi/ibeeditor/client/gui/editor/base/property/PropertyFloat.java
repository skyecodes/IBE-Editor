package com.github.franckyi.ibeeditor.client.gui.editor.base.property;

import com.github.franckyi.guapi.node.FloatField;
import com.github.franckyi.guapi.node.NumberField;

import java.util.function.Consumer;

public class PropertyFloat extends PropertyNumber<Float> {

    public PropertyFloat(String name, float initialValue, Consumer<Float> action) {
        super(name, initialValue, action);
    }

    public PropertyFloat(String name, float initialValue, Consumer<Float> action, int labelSize) {
        super(name, initialValue, action, labelSize);
    }

    public PropertyFloat(String name, float initialValue, Consumer<Float> action, float min, float max) {
        super(name, initialValue, action, min, max);
    }

    @Override
    protected NumberField<Float> createField(Float value) {
        return new FloatField(value);
    }
}
