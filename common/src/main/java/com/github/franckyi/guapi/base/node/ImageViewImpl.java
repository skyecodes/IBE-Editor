package com.github.franckyi.guapi.base.node;

import com.github.franckyi.guapi.api.node.ImageView;
import com.github.franckyi.guapi.api.node.builder.ImageViewBuilder;
import net.minecraft.resources.ResourceLocation;

public class ImageViewImpl extends AbstractImageView implements ImageViewBuilder {
    public ImageViewImpl(ResourceLocation textureId) {
        super(textureId);
    }

    public ImageViewImpl(ResourceLocation textureId, int imageWidth, int imageHeight) {
        super(textureId, imageWidth, imageHeight);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Class<?> getType() {
        return ImageView.class;
    }

    @Override
    public String toString() {
        return "ImageView{\"" + getTextureId() + "\"}";
    }
}
