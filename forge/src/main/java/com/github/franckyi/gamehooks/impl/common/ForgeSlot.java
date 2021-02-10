package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.Slot;
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
    public boolean isPlayerInventory() {
        return slot.inventory instanceof PlayerInventory;
    }

    @Override
    public int getId() {
        return slot.slotNumber;
    }

    @Override
    public Item getStack() {
        if (stack == null) {
            stack = new ForgeItem(slot.getStack());
        }
        return stack;
    }
}
