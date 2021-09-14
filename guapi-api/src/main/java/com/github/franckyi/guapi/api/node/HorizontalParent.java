package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.BooleanProperty;

public interface HorizontalParent {
    default boolean isFillHeight() {
        return fillHeightProperty().getValue();
    }

    BooleanProperty fillHeightProperty();

    default void setFillHeight(boolean value) {
        fillHeightProperty().setValue(value);
    }
}
