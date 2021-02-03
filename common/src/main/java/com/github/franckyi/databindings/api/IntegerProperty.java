package com.github.franckyi.databindings.api;

public interface IntegerProperty extends Property<Integer>, ObservableIntegerValue {
    default void setValue(int value) {
        set(value);
    }
}
