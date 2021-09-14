package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.gameadapter.api.common.IIdentifier;

public class ImageListSelectionItemModel extends ListSelectionItemModel {
    private final IIdentifier textureId;

    public ImageListSelectionItemModel(String name, IIdentifier id, IIdentifier textureId) {
        super(name, id);
        this.textureId = textureId;
    }

    public IIdentifier getTextureId() {
        return textureId;
    }

    @Override
    public Type getType() {
        return Type.IMAGE;
    }
}
