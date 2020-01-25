package com.github.franckyi.ibeeditor.client.gui.editor.base.property;

import com.github.franckyi.guapi.node.DoubleField;

import java.util.function.Consumer;

public class PropertyDouble extends LabeledProperty<Double> {

    protected DoubleField doubleField;

    public PropertyDouble(String name, Double initialValue, Consumer<Double> action) {
        super(name, initialValue, action);
    }

    public PropertyDouble(String name, Double initialValue, Consumer<Double> action, int labelSize) {
        super(name, initialValue, action);
        nameLabel.setPrefWidth(labelSize);
    }

    public PropertyDouble(String name, Double initialValue, Consumer<Double> action, int min, int max) {
        this(name, initialValue, action);
        doubleField.setMin(min);
        doubleField.setMax(max);
    }

    @Override
    public Double getValue() {
        return doubleField.getValue();
    }

    @Override
    protected void setValue(Double value) {
        doubleField.setValue(value);
    }

    @Override
    protected void build() {
        super.build();
        this.addAll(doubleField = new DoubleField(initialValue));
    }

    @Override
    public void updateSize(int listWidth) {
        doubleField.setPrefWidth(listWidth - nameLabel.getWidth() - OFFSET - 64);
    }
}
