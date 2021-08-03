package com.github.franckyi.ibeeditor.base.client.mvc.model;

import java.util.function.Supplier;

public class SpriteListSelectionItemModel extends ListSelectionItemModel {
    private final Supplier<Object> spriteFactory;

    public SpriteListSelectionItemModel(String name, String id, Supplier<Object> spriteFactory) {
        super(name, id);
        this.spriteFactory = spriteFactory;
    }

    public Supplier<Object> getSpriteFactory() {
        return spriteFactory;
    }

    @Override
    public Type getType() {
        return Type.SPRITE;
    }
}
