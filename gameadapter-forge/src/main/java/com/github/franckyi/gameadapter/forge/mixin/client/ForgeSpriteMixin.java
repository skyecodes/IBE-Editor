package com.github.franckyi.gameadapter.forge.mixin.client;

import com.github.franckyi.gameadapter.api.client.ISprite;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.client.extensions.IForgeTextureAtlasSprite;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TextureAtlasSprite.class)
public abstract class ForgeSpriteMixin implements AutoCloseable, IForgeTextureAtlasSprite, ISprite {
}
