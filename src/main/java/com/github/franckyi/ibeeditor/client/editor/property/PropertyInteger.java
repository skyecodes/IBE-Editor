package com.github.franckyi.ibeeditor.client.editor.property;

import com.github.franckyi.guapi.node.IntegerField;

import java.util.function.Consumer;

public class PropertyInteger extends LabeledCategory<Integer> {

    protected IntegerField integerField;

    public PropertyInteger(String name, Integer initialValue, Consumer<Integer> action) {
        super(name, initialValue, action);
    }

    public PropertyInteger(String name, Integer initialValue, Consumer<Integer> action, int labelSize) {
        super(name, initialValue, action);
        nameLabel.setPrefWidth(labelSize);
    }

    public PropertyInteger(String name, Integer initialValue, Consumer<Integer> action, int min, int max) {
        this(name, initialValue, action);
        integerField.setMin(min);
        integerField.setMax(max);
    }

    @Override
    public Integer getValue() {
        return integerField.getValue();
    }

    @Override
    protected void setValue(Integer value) {
        integerField.setValue(value);
    }

    @Override
    protected void build() {
        super.build();
        this.addAll(integerField = new IntegerField(initialValue));
    }

    @Override
    public void updateSize(int listWidth) {
        integerField.setPrefWidth(listWidth - 66 - nameLabel.getWidth());
    }
}
