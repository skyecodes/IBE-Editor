package com.github.franckyi.guapi.common.data;

public interface Property<T> extends ObservableValue<T> {
    void setValue(T value);
    void bind(ObservableValue<? extends T> observableValue);
    void unbind();
    boolean isBound();
    void bindBidirectional(Property<T> property);
    void unbindBidirectional(Property<T> property);
}
