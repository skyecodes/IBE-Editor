package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.api.node.SpriteView;

public abstract class AbstractSpriteView extends AbstractControl implements SpriteView {
    private final ObjectProperty<Object> spriteProperty = ObjectProperty.create();
    private final IntegerProperty imageWidthProperty = IntegerProperty.create();
    private final IntegerProperty imageHeightProperty = IntegerProperty.create();

    protected AbstractSpriteView() {
    }

    protected AbstractSpriteView(Object sprite) {
        setSprite(sprite);
    }

    protected AbstractSpriteView(Object sprite, int imageWidth, int imageHeight) {
        this(sprite);
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);
        setPrefWidth(imageWidth);
        setPrefHeight(imageHeight);
    }

    @Override
    public ObjectProperty<Object> spriteProperty() {
        return spriteProperty;
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
