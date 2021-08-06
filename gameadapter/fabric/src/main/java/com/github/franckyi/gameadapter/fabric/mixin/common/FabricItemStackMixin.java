package com.github.franckyi.gameadapter.fabric.mixin.common;

import com.github.franckyi.gameadapter.api.common.IItemStack;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemStack.class)
public abstract class FabricItemStackMixin implements IItemStack {
    @Shadow
    public abstract NbtCompound writeNbt(NbtCompound nbt);

    @Shadow
    public abstract Item getItem();

    @Override
    public ICompoundTag getData() {
        return (ICompoundTag) writeNbt(new NbtCompound());
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
        return getItem() instanceof DyeableItem;
    }
}
