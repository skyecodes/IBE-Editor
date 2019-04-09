package com.github.franckyi.ibeeditor.editor.property;

import com.github.franckyi.guapi.node.IntegerField;

import java.util.function.Consumer;

public class IntegerProperty extends EmptyProperty<Integer> {

    protected IntegerField integerField;

    public IntegerProperty(String name, Integer initialValue, Consumer<Integer> action) {
        super(name, initialValue, action);
    }

    public IntegerProperty(String name, Integer initialValue, Consumer<Integer> action, int min, int max) {
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
        this.getNode().getChildren().add(integerField = new IntegerField(initialValue));
    }

    @Override
    protected void updateSize(int listWidth) {
        integerField.setPrefWidth(listWidth - 116);
    }
}
