package com.github.franckyi.gameadapter.fabric.mixin.common;

import com.github.franckyi.gameadapter.api.common.IEnchantment;
import com.github.franckyi.gameadapter.api.common.IItemStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Enchantment.class)
public abstract class FabricEnchantmentMixin implements IEnchantment {
    @Shadow
    public abstract boolean isCursed();

    @Shadow
    public abstract boolean isAcceptableItem(ItemStack stack);

    @Shadow
    public abstract String getTranslationKey();

    @Override
    public boolean isCurse() {
        return isCursed();
    }

    @Override
    public boolean canApply(IItemStack itemStack) {
        return isAcceptableItem(ItemStack.class.cast(itemStack));
    }

    @Override
    public String getName() {
        return getTranslationKey();
    }
}
