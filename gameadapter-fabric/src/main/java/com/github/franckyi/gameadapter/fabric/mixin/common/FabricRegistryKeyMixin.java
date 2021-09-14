package com.github.franckyi.gameadapter.fabric.mixin.common;

import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.registry.IRegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RegistryKey.class)
public abstract class FabricRegistryKeyMixin implements IRegistryKey {
    @Shadow
    public abstract Identifier getValue();

    @Override
    public IIdentifier getId() {
        return (IIdentifier) getValue();
    }
}
