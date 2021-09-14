package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.gameadapter.api.client.ISprite;
import com.github.franckyi.gameadapter.api.common.IIdentifier;

import java.util.function.Supplier;

public class SpriteListSelectionItemModel extends ListSelectionItemModel {
    private final Supplier<ISprite> spriteFactory;

    public SpriteListSelectionItemModel(String name, IIdentifier id, Supplier<ISprite> spriteFactory) {
        super(name, id);
        this.spriteFactory = spriteFactory;
    }

    public Supplier<ISprite> getSpriteFactory() {
        return spriteFactory;
    }

    @Override
    public Type getType() {
        return Type.SPRITE;
    }
}
