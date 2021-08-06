package com.github.franckyi.gameadapter.fabric.mixin.common;

import com.github.franckyi.gameadapter.api.common.IRegistryValue;
import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StatusEffect.class)
public abstract class FabricStatusEffectMixin implements IRegistryValue {
    @Shadow
    public abstract String getTranslationKey();

    @Override
    public String getName() {
        return getTranslationKey();
    }
}
