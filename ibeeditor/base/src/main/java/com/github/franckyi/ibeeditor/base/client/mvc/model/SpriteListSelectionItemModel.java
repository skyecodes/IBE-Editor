package com.github.franckyi.ibeeditor.base.client.mvc.model;

public class SpriteListSelectionItemModel extends ListSelectionItemModel {
    private final Object sprite;

    public SpriteListSelectionItemModel(String name, String id, Object sprite) {
        super(name, id);
        this.sprite = sprite;
    }

    public Object getSprite() {
        return sprite;
    }

    @Override
    public Type getType() {
        return Type.SPRITE;
    }
}
