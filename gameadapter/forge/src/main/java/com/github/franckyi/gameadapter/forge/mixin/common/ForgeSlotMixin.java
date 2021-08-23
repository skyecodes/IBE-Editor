package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.item.ISlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Slot.class)
public abstract class ForgeSlotMixin implements ISlot {
    @Shadow
    public abstract boolean hasItem();

    @Shadow
    public abstract ItemStack getItem();

    @Shadow
    @Final
    public IInventory container;

    @Shadow
    public abstract int getSlotIndex();

    @Override
    public boolean hasStack() {
        return hasItem();
    }

    @Override
    public boolean isInPlayerInventory() {
        return container instanceof PlayerInventory;
    }

    @Override
    public int getIndex() {
        return getSlotIndex();
    }

    @Override
    public IItemStack getStack() {
        return IItemStack.class.cast(getItem());
    }
}
