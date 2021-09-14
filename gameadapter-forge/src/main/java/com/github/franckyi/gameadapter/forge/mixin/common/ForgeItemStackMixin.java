package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import org.spongepowered.asm.mixin.*;

@Mixin(ItemStack.class)
@Implements(@Interface(iface = IItemStack.class, prefix = "proxy$"))
public abstract class ForgeItemStackMixin {
    @Shadow
    public abstract boolean isEmpty();

    @Shadow
    public abstract CompoundNBT save(CompoundNBT p_77955_1_);

    @Shadow
    public abstract Item getItem();

    @Intrinsic
    public boolean proxy$isEmpty() {
        return isEmpty();
    }

    public ICompoundTag proxy$getData() {
        return (ICompoundTag) save(new CompoundNBT());
    }

    public boolean proxy$isBlockItem() {
        return getItem() instanceof BlockItem;
    }

    public boolean proxy$isPotionItem() {
        return getItem() instanceof PotionItem;
    }

    public boolean proxy$isDyeableItem() {
        return getItem() instanceof IDyeableArmorItem;
    }
}
