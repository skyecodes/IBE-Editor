package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.client.ISprite;

import java.util.function.Supplier;

public interface SpriteView extends Control {
    default Supplier<ISprite> getSpriteFactory() {
        return spriteFactoryProperty().getValue();
    }

    ObjectProperty<Supplier<ISprite>> spriteFactoryProperty();

    default void setSpriteFactory(Supplier<ISprite> value) {
        spriteFactoryProperty().setValue(value);
    }

    ISprite getSprite();

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
