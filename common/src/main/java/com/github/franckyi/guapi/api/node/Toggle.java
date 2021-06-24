package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.BooleanProperty;

public interface Toggle {
    default boolean isActive() {
        return activeProperty().get();
    }

    BooleanProperty activeProperty();

    default void setActive(boolean value) {
        activeProperty().setValue(value);
    }

    default void toggle() {
        activeProperty().toggle();
    }
}
