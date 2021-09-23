package com.github.franckyi.ibeeditor.base.client.mvc.model;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class SpriteListSelectionItemModel extends ListSelectionItemModel {
    private final Supplier<TextureAtlasSprite> spriteFactory;

    public SpriteListSelectionItemModel(String name, ResourceLocation id, Supplier<TextureAtlasSprite> spriteFactory) {
        super(name, id);
        this.spriteFactory = spriteFactory;
    }

    public Supplier<TextureAtlasSprite> getSpriteFactory() {
        return spriteFactory;
    }

    @Override
    public Type getType() {
        return Type.SPRITE;
    }
}
