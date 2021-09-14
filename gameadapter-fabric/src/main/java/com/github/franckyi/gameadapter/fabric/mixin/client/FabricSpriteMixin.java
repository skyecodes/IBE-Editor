package com.github.franckyi.gameadapter.fabric.mixin.client;

import com.github.franckyi.gameadapter.api.client.ISprite;
import net.minecraft.client.texture.Sprite;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Sprite.class)
public abstract class FabricSpriteMixin implements AutoCloseable, ISprite {
}
