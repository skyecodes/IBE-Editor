package com.github.franckyi.guapi.common.data;

public class SimpleIntegerProperty extends SimpleObjectProperty<Integer> implements IntegerProperty {
    public SimpleIntegerProperty() {
        this(0);
    }

    public SimpleIntegerProperty(int value) {
        super(value);
    }
}
