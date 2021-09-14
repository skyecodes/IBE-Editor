package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.BooleanProperty;

public interface TexturedButton extends ImageView {
    default boolean isDrawButton() {
        return drawButtonProperty().getValue();
    }

    BooleanProperty drawButtonProperty();

    default void setDrawButton(boolean value) {
        drawButtonProperty().setValue(value);
    }
}
