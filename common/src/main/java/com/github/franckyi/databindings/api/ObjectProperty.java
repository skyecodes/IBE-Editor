package com.github.franckyi.databindings.api;

public interface ObjectProperty<T> extends Property<T>, ObservableObjectValue<T> {
    default void setValue(T value) {
        set(value);
    }
}
