package com.github.franckyi.guapi.common.data;

public interface ObjectProperty<T> extends ObservableValue<T> {
    void setValue(T value);

    void bind(ObservableValue<? extends T> observableValue);

    void unbind();

    boolean isBound();

    void bindBidirectional(ObjectProperty<T> property);

    void unbindBidirectional(ObjectProperty<T> property);
}
