package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;

public interface Toggle {
    default boolean isActive() {
        return activeProperty().getValue();
    }

    BooleanProperty activeProperty();

    default void setActive(boolean value) {
        activeProperty().setValue(value);
    }

    default void toggle() {
        activeProperty().toggle();
    }

    default int getBorderColor() {
        return borderColorProperty().getValue();
    }

    IntegerProperty borderColorProperty();

    default void setBorderColor(int value) {
        borderColorProperty().setValue(value);
    }
}
