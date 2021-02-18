package com.github.franckyi.gamehooks.util.common.tag;

import com.github.franckyi.databindings.api.ObjectProperty;

public interface Tag<T> {
    default T getValue() {
        return valueProperty().getValue();
    }

    ObjectProperty<T> valueProperty();

    default void setValue(T value) {
        valueProperty().setValue(value);
    }

    byte getType();
}
