package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.api.node.SpriteView;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

import java.util.function.Supplier;

public abstract class AbstractSpriteView extends AbstractControl implements SpriteView {
    private final ObjectProperty<Supplier<TextureAtlasSprite>> spriteFactoryProperty = ObjectProperty.create();
    private final IntegerProperty imageWidthProperty = IntegerProperty.create();
    private final IntegerProperty imageHeightProperty = IntegerProperty.create();
    private TextureAtlasSprite cachedSprite;

    protected AbstractSpriteView() {
        spriteFactoryProperty().addListener(() -> cachedSprite = null);
    }

    protected AbstractSpriteView(Supplier<TextureAtlasSprite> spriteFactory) {
        this();
        setSpriteFactory(spriteFactory);
    }

    protected AbstractSpriteView(Supplier<TextureAtlasSprite> spriteFactory, int imageWidth, int imageHeight) {
        this(spriteFactory);
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);
        setPrefWidth(imageWidth);
        setPrefHeight(imageHeight);
    }

    @Override
    public ObjectProperty<Supplier<TextureAtlasSprite>> spriteFactoryProperty() {
        return spriteFactoryProperty;
    }

    @Override
    public TextureAtlasSprite getSprite() {
        if (cachedSprite == null) {
            cachedSprite = getSpriteFactory().get();
        }
        return cachedSprite;
    }

    @Override
    public IntegerProperty imageWidthProperty() {
        return imageWidthProperty;
    }

    @Override
    public IntegerProperty imageHeightProperty() {
        return imageHeightProperty;
    }
}
