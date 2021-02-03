package com.github.franckyi.databindings.api;

public interface Property<T> extends ObservableValue<T> {
    void set(T value);

    void bind(ObservableValue<? extends T> observableValue);

    void unbind();

    boolean isBound();

    void bindBidirectional(Property<T> property);

    void unbindBidirectional(Property<T> property);
}
