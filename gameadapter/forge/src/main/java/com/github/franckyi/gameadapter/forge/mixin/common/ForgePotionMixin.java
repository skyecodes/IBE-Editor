package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.IRegistryValue;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Potion.class)
public abstract class ForgePotionMixin implements IRegistryValue {
    @Shadow
    public abstract String getName(String p_185174_1_);

    @Override
    public String getName() {
        return getName(Items.POTION.getDescriptionId() + ".effect.");
    }
}
