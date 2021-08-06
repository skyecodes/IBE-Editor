package com.github.franckyi.guapi.base.node;

import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.guapi.api.node.builder.ImageViewBuilder;
import com.github.franckyi.guapi.api.util.NodeType;

public class ImageViewImpl extends AbstractImageView implements ImageViewBuilder {
    public ImageViewImpl(IIdentifier textureId) {
        super(textureId);
    }

    public ImageViewImpl(IIdentifier textureId, int imageWidth, int imageHeight) {
        super(textureId, imageWidth, imageHeight);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected NodeType<?> getType() {
        return NodeType.IMAGE_VIEW;
    }

    @Override
    public String toString() {
        return "ImageView{\"" + getTextureId() + "\"}";
    }
}
