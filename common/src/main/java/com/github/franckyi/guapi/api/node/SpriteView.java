package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

import java.util.function.Supplier;

public interface SpriteView extends Control {
    default Supplier<TextureAtlasSprite> getSpriteFactory() {
        return spriteFactoryProperty().getValue();
    }

    ObjectProperty<Supplier<TextureAtlasSprite>> spriteFactoryProperty();

    default void setSpriteFactory(Supplier<TextureAtlasSprite> value) {
        spriteFactoryProperty().setValue(value);
    }

    TextureAtlasSprite getSprite();

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
