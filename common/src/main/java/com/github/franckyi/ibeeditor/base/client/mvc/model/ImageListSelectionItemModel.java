package com.github.franckyi.ibeeditor.base.client.mvc.model;

import net.minecraft.resources.ResourceLocation;

public class ImageListSelectionItemModel extends ListSelectionItemModel {
    private final ResourceLocation textureId;

    public ImageListSelectionItemModel(String name, ResourceLocation id, ResourceLocation textureId) {
        super(name, id);
        this.textureId = textureId;
    }

    public ResourceLocation getTextureId() {
        return textureId;
    }

    @Override
    public Type getType() {
        return Type.IMAGE;
    }
}
