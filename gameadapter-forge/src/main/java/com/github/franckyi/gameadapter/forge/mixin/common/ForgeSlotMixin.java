package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.item.ISlot;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
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
    public Container container;

    @Shadow(remap = false) // method added by Forge
    public abstract int getSlotIndex();

    public boolean proxy$hasStack() {
        return hasItem();
    }

    public boolean proxy$isInPlayerInventory() {
        return container instanceof Inventory;
    }

    public int proxy$getIndex() {
        return getSlotIndex();
    }

    public IItemStack proxy$getStack() {
        return IItemStack.class.cast(getItem());
    }
}
