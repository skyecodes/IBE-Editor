package com.github.franckyi.ibeeditor.client.screen.model.selection.element;

import net.minecraft.resources.ResourceLocation;

public class ImageListSelectionElementModel extends ListSelectionElementModel {
    private final ResourceLocation textureId;

    public ImageListSelectionElementModel(String name, ResourceLocation id, ResourceLocation textureId) {
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
