package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.guapi.api.node.ImageView;

public abstract class AbstractImageView extends AbstractControl implements ImageView {
    private final StringProperty textureIdProperty = Bindings.getPropertyFactory().ofString();
    private final IntegerProperty imageXProperty = Bindings.getPropertyFactory().ofInteger();
    private final IntegerProperty imageYProperty = Bindings.getPropertyFactory().ofInteger();
    private final IntegerProperty imageWidthProperty = Bindings.getPropertyFactory().ofInteger();
    private final IntegerProperty imageHeightProperty = Bindings.getPropertyFactory().ofInteger();

    public AbstractImageView(String textureId) {
        this(textureId, 0, 0);
    }

    public AbstractImageView(String textureId, int imageWidth, int imageHeight) {
        setTextureId(textureId);
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);
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
