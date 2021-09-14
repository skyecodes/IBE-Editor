package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.registry.IRegistryValue;
import net.minecraft.world.effect.MobEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MobEffect.class)
public abstract class ForgeEffectMixin implements IRegistryValue {
    @Shadow
    public abstract String getDescriptionId();

    @Override
    public String getName() {
        return getDescriptionId();
    }
}
