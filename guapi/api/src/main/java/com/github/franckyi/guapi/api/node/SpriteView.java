package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;

import java.util.function.Supplier;

public interface SpriteView extends Control {
    default Supplier<Object> getSpriteFactory() {
        return spriteFactoryProperty().getValue();
    }

    ObjectProperty<Supplier<Object>> spriteFactoryProperty();

    default void setSpriteFactory(Supplier<Object> value) {
        spriteFactoryProperty().setValue(value);
    }

    Object getSprite();

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
