package com.github.franckyi.ibeeditor.base.client.mvc.model;

public class ImageListSelectionItemModel extends ListSelectionItemModel {
    private final String textureId;

    public ImageListSelectionItemModel(String name, String id, String textureId) {
        super(name, id);
        this.textureId = textureId;
    }

    public String getTextureId() {
        return textureId;
    }

    @Override
    public Type getType() {
        return Type.IMAGE;
    }
}
