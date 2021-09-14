package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.item.ISlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.*;

@Mixin(Slot.class)
@Implements(@Interface(iface = ISlot.class, prefix = "proxy$"))
public abstract class ForgeSlotMixin {
    @Shadow
    public abstract boolean hasItem();

    @Shadow
    public abstract ItemStack getItem();

    @Shadow
    @Final
    public IInventory container;

    @Shadow(remap = false) // method added by Forge
    public abstract int getSlotIndex();

    public boolean proxy$hasStack() {
        return hasItem();
    }

    public boolean proxy$isInPlayerInventory() {
        return container instanceof PlayerInventory;
    }

    public int proxy$getIndex() {
        return getSlotIndex();
    }

    public IItemStack proxy$getStack() {
        return IItemStack.class.cast(getItem());
    }
}
