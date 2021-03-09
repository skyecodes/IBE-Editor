package com.github.franckyi.minecraft.impl.common;

import com.github.franckyi.minecraft.api.common.Slot;
import com.github.franckyi.minecraft.api.common.world.Item;
import com.github.franckyi.minecraft.impl.common.world.ForgeItem;
import net.minecraft.entity.player.PlayerInventory;

public class ForgeSlot implements Slot {
    private final net.minecraft.inventory.container.Slot slot;
    private Item stack;

    public ForgeSlot(net.minecraft.inventory.container.Slot slot) {
        this.slot = slot;
    }

    @Override
    public boolean hasStack() {
        return slot != null && slot.getHasStack();
    }

    @Override
    public boolean isInPlayerInventory() {
        return slot.inventory instanceof PlayerInventory;
    }

    @Override
    public int getIndex() {
        return slot.getSlotIndex();
    }

    @Override
    public Item getStack() {
        if (stack == null) {
            stack = new ForgeItem(slot.getStack());
        }
        return stack;
    }
}
