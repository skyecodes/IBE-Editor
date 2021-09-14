package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.registry.IRegistryKey;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RegistryKey.class)
public abstract class ForgeRegistryKeyMixin implements IRegistryKey {
    @Shadow
    public abstract ResourceLocation location();

    @Override
    public IIdentifier getId() {
        return (IIdentifier) location();
    }
}
