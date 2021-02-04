package com.github.franckyi.databindings.api;

public interface ObservableObjectValue<T> extends ObservableValue<T> {
    default T getValue() {
        return get();
    }

    default boolean hasValue() {
        return get() != null;
    }
}
