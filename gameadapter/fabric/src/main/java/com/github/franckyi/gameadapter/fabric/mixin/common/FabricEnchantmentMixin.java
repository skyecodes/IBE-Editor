package com.github.franckyi.gameadapter.fabric.mixin.common;

import com.github.franckyi.gameadapter.api.common.item.IEnchantment;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
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

    @Shadow @Final public EnchantmentTarget type;

    @Override
    public boolean isCurse() {
        return isCursed();
    }

    @Override
    public boolean canApply(IItemStack itemStack) {
        return isAcceptableItem(ItemStack.class.cast(itemStack));
    }

    @Override
    public int getTarget() {
        return type.ordinal();
    }

    @Override
    public String getName() {
        return getTranslationKey();
    }
}
