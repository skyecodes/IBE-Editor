package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.guapi.api.node.ImageView;

public abstract class AbstractImageView extends AbstractControl implements ImageView {
    private final StringProperty textureIdProperty = StringProperty.create();
    private final IntegerProperty imageXProperty = IntegerProperty.create();
    private final IntegerProperty imageYProperty = IntegerProperty.create();
    private final IntegerProperty imageWidthProperty = IntegerProperty.create();
    private final IntegerProperty imageHeightProperty = IntegerProperty.create();

    public AbstractImageView(String textureId) {
        this(textureId, 0, 0);
    }

    public AbstractImageView(String textureId, int imageWidth, int imageHeight) {
        setTextureId(textureId);
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);
        setPrefWidth(imageWidth);
        setPrefHeight(imageHeight);
    }

    @Override
    public StringProperty textureIdProperty() {
        return textureIdProperty;
    }

    @Override
    public IntegerProperty imageXProperty() {
        return imageXProperty;
    }

    @Override
    public IntegerProperty imageYProperty() {
        return imageYProperty;
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
