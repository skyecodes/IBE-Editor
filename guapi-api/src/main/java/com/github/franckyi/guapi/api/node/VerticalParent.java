package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.BooleanProperty;

public interface VerticalParent {
    default boolean isFillWidth() {
        return fillWidthProperty().getValue();
    }

    BooleanProperty fillWidthProperty();

    default void setFillWidth(boolean value) {
        fillWidthProperty().setValue(value);
    }
}
