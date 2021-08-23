package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.item.IEnchantment;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Enchantment.class)
public abstract class ForgeEnchantmentMixin extends ForgeRegistryEntry<Enchantment> implements IEnchantment {
    @Shadow
    public abstract boolean canEnchant(ItemStack p_92089_1_);

    @Shadow
    public abstract String getDescriptionId();

    @Override
    public boolean canApply(IItemStack itemStack) {
        return canEnchant(ItemStack.class.cast(itemStack));
    }

    @Override
    public String getName() {
        return getDescriptionId();
    }
}
