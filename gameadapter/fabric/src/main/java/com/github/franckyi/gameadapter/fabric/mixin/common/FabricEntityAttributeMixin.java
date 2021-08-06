package com.github.franckyi.gameadapter.fabric.mixin.common;

import com.github.franckyi.gameadapter.api.common.IRegistryValue;
import net.minecraft.entity.attribute.EntityAttribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityAttribute.class)
public abstract class FabricEntityAttributeMixin implements IRegistryValue {
    @Shadow
    public abstract String getTranslationKey();

    @Override
    public String getName() {
        return getTranslationKey();
    }
}
