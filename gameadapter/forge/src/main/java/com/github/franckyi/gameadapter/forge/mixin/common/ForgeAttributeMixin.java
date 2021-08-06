package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.IRegistryValue;
import net.minecraft.entity.ai.attributes.Attribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Attribute.class)
public abstract class ForgeAttributeMixin implements IRegistryValue {
    @Shadow
    public abstract String getDescriptionId();

    @Override
    public String getName() {
        return getDescriptionId();
    }
}
