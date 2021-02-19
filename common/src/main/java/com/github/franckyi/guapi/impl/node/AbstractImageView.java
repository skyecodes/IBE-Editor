package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.guapi.api.node.ImageView;

public abstract class AbstractImageView extends AbstractControl implements ImageView {
    private final StringProperty textureIdProperty = PropertyFactory.ofString();
    private final IntegerProperty imageXProperty = PropertyFactory.ofInteger();
    private final IntegerProperty imageYProperty = PropertyFactory.ofInteger();
    private final IntegerProperty imageWidthProperty = PropertyFactory.ofInteger();
    private final IntegerProperty imageHeightProperty = PropertyFactory.ofInteger();

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
