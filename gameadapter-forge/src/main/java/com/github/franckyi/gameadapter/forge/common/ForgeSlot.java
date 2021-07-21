package com.github.franckyi.gameadapter.forge.common;

import com.github.franckyi.gameadapter.api.common.Slot;
import com.github.franckyi.gameadapter.api.common.world.Item;
import com.github.franckyi.gameadapter.forge.common.world.ForgeItem;
import net.minecraft.entity.player.PlayerInventory;

public class ForgeSlot implements Slot {
    private final net.minecraft.inventory.container.Slot slot;
    private Item stack;

    public ForgeSlot(net.minecraft.inventory.container.Slot slot) {
        this.slot = slot;
    }

    @Override
    public boolean hasStack() {
        return slot != null && slot.hasItem();
    }

    @Override
    public boolean isInPlayerInventory() {
        return slot.container instanceof PlayerInventory;
    }

    @Override
    public int getIndex() {
        return slot.getSlotIndex();
    }

    @Override
    public Item getStack() {
        if (stack == null) {
            stack = new ForgeItem(slot.getItem());
        }
        return stack;
    }
}
