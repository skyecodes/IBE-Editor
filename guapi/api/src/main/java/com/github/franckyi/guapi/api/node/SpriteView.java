package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;

public interface SpriteView extends Control {
    default Object getSprite() {
        return spriteProperty().getValue();
    }

    ObjectProperty<Object> spriteProperty();

    default void setSprite(Object value) {
        spriteProperty().setValue(value);
    }

    default int getImageWidth() {
        return imageWidthProperty().getValue();
    }

    IntegerProperty imageWidthProperty();

    default void setImageWidth(int value) {
        imageWidthProperty().setValue(value);
    }

    default int getImageHeight() {
        return imageHeightProperty().getValue();
    }

    IntegerProperty imageHeightProperty();

    default void setImageHeight(int value) {
        imageHeightProperty().setValue(value);
    }
}
