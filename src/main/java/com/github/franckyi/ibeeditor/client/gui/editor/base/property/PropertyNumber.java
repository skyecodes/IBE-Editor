package com.github.franckyi.ibeeditor.client.gui.editor.base.property;

import com.github.franckyi.guapi.node.NumberField;

import java.util.function.Consumer;

public abstract class PropertyNumber<T extends Number & Comparable<T>> extends LabeledProperty<T> {

    protected NumberField<T> numberField;

    protected PropertyNumber(String name, T initialValue, Consumer<T> action) {
        super(name, initialValue, action);
    }

    protected PropertyNumber(String name, T initialValue, Consumer<T> action, int labelSize) {
        super(name, initialValue, action);
        nameLabel.setPrefWidth(labelSize);
    }

    protected PropertyNumber(String name, T initialValue, Consumer<T> action, T min, T max) {
        this(name, initialValue, action);
        numberField.setMin(min);
        numberField.setMax(max);
    }

    @Override
    public T getValue() {
        return numberField.getValue();
    }

    @Override
    protected void setValue(T value) {
        numberField.setValue(value);
    }

    @Override
    protected void build() {
        super.build();
        this.addAll(numberField = this.createField(initialValue));
    }

    @Override
    public void updateSize(int listWidth) {
        numberField.setPrefWidth(listWidth - nameLabel.getWidth() - OFFSET - 64);
    }

    public NumberField<T> getNumberField() {
        return numberField;
    }

    protected abstract NumberField<T> createField(T value);
}
