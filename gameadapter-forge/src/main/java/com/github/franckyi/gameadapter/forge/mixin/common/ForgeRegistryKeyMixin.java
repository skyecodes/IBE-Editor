package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.registry.IRegistryKey;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ResourceKey.class)
public abstract class ForgeRegistryKeyMixin implements IRegistryKey {
    @Shadow
    public abstract ResourceLocation location();

    @Override
    public IIdentifier getId() {
        return (IIdentifier) location();
    }
}
