package com.github.franckyi.ibeeditor.client.gui.editor.base.property;

import com.github.franckyi.guapi.node.DoubleField;
import com.github.franckyi.guapi.node.NumberField;

import java.util.function.Consumer;

public class PropertyDouble extends PropertyNumber<Double> {

    public PropertyDouble(String name, double initialValue, Consumer<Double> action) {
        super(name, initialValue, action);
    }

    public PropertyDouble(String name, double initialValue, Consumer<Double> action, int labelSize) {
        super(name, initialValue, action, labelSize);
    }

    public PropertyDouble(String name, double initialValue, Consumer<Double> action, double min, double max) {
        super(name, initialValue, action, min, max);
    }

    @Override
    protected NumberField<Double> createField(Double value) {
        return new DoubleField(value);
    }
}
