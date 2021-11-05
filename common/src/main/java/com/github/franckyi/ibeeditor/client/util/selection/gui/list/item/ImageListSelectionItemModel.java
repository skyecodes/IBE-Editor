package com.github.franckyi.ibeeditor.client.util.selection.gui.list.item;

import com.github.franckyi.ibeeditor.client.util.selection.gui.list.ListSelectionItemModel;
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
