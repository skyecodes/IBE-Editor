package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.registry.IRegistryValue;
import net.minecraft.potion.Effect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Effect.class)
public abstract class ForgeEffectMixin implements IRegistryValue {
    @Shadow
    public abstract String getDescriptionId();

    @Override
    public String getName() {
        return getDescriptionId();
    }
}
