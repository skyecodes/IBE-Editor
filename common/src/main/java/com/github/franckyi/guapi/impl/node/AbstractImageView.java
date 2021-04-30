package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.guapi.api.node.ImageView;

public abstract class AbstractImageView extends AbstractControl implements ImageView {
    private final StringProperty textureIdProperty = DataBindings.getPropertyFactory().createStringProperty();
    private final IntegerProperty imageXProperty = DataBindings.getPropertyFactory().createIntegerProperty();
    private final IntegerProperty imageYProperty = DataBindings.getPropertyFactory().createIntegerProperty();
    private final IntegerProperty imageWidthProperty = DataBindings.getPropertyFactory().createIntegerProperty();
    private final IntegerProperty imageHeightProperty = DataBindings.getPropertyFactory().createIntegerProperty();

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
