package com.github.franckyi.gameadapter.fabric.mixin.common;

import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.item.ISlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.*;

@Mixin(net.minecraft.screen.slot.Slot.class)
@Implements(@Interface(iface = ISlot.class, prefix = "proxy$"))
public abstract class FabricSlotMixin {
    @Shadow
    public abstract boolean hasStack();

    @Shadow
    @Final
    public Inventory inventory;

    @Shadow
    @Final
    private int index;

    @Shadow
    public abstract ItemStack getStack();

    @Intrinsic
    public boolean proxy$hasStack() {
        return hasStack();
    }

    public boolean proxy$isInPlayerInventory() {
        return inventory instanceof PlayerInventory;
    }

    public int proxy$getIndex() {
        return index;
    }

    public IItemStack proxy$getStack() {
        return IItemStack.class.cast(getStack());
    }
}
