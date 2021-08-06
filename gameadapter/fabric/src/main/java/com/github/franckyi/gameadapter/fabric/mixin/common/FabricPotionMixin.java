package com.github.franckyi.gameadapter.fabric.mixin.common;

import com.github.franckyi.gameadapter.api.common.IRegistryValue;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Potion.class)
public abstract class FabricPotionMixin implements IRegistryValue {
    @Shadow
    public abstract String finishTranslationKey(String prefix);

    @Override
    public String getName() {
        return finishTranslationKey(Items.POTION.getTranslationKey() + ".effect.");
    }
}
