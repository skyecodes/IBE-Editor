package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.builder.ImageViewBuilder;
import com.github.franckyi.guapi.util.NodeType;

public class ImageViewImpl extends AbstractImageView implements ImageViewBuilder {
    public ImageViewImpl(String textureId) {
        super(textureId);
    }

    public ImageViewImpl(String textureId, int imageWidth, int imageHeight) {
        super(textureId, imageWidth, imageHeight);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<?> getType() {
        return NodeType.IMAGEVIEW;
    }

    @Override
    public String toString() {
        return "ImageView{\"" + getTextureId() + "\"}";
    }
}
