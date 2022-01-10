package com.github.franckyi.ibeeditor.client.screen.model.selection.element;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class SpriteListSelectionElementModel extends ListSelectionElementModel {
    private final Supplier<TextureAtlasSprite> spriteFactory;

    public SpriteListSelectionElementModel(String name, ResourceLocation id, Supplier<TextureAtlasSprite> spriteFactory) {
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
