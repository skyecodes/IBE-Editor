package com.github.franckyi.gameadapter.fabric.mixin.common;

import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.item.ISlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(net.minecraft.screen.slot.Slot.class)
public abstract class FabricSlotMixin implements ISlot {
    @Shadow
    @Final
    public Inventory inventory;

    @Shadow
    @Final
    private int index;

    @Shadow
    public abstract ItemStack shadow$getStack();

    @Override
    public boolean isInPlayerInventory() {
        return inventory instanceof PlayerInventory;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public IItemStack getStack() {
        return IItemStack.class.cast(shadow$getStack());
    }
}
