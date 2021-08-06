package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.IItemStack;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemStack.class)
public abstract class ForgeItemStackMixin implements IItemStack {
    @Shadow
    public abstract CompoundNBT save(CompoundNBT p_77955_1_);

    @Shadow
    public abstract Item getItem();

    @Override
    public ICompoundTag getData() {
        return (ICompoundTag) save(new CompoundNBT());
    }

    @Override
    public boolean isBlockItem() {
        return getItem() instanceof BlockItem;
    }

    @Override
    public boolean isPotionItem() {
        return getItem() instanceof PotionItem;
    }

    @Override
    public boolean isDyeableItem() {
        return getItem() instanceof IDyeableArmorItem;
    }
}
