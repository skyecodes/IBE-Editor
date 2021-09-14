package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.item.IEnchantment;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.spongepowered.asm.mixin.*;

@Mixin(Enchantment.class)
@Implements(@Interface(iface = IEnchantment.class, prefix = "proxy$"))
public abstract class ForgeEnchantmentMixin extends ForgeRegistryEntry<Enchantment> {
    @Shadow
    public abstract boolean isCurse();

    @Shadow
    public abstract boolean canEnchant(ItemStack p_92089_1_);

    @Shadow
    public abstract String getDescriptionId();

    @Intrinsic
    public boolean proxy$isCurse() {
        return isCurse();
    }

    public boolean proxy$canApply(IItemStack itemStack) {
        return canEnchant(ItemStack.class.cast(itemStack));
    }

    public String proxy$getName() {
        return getDescriptionId();
    }
}
