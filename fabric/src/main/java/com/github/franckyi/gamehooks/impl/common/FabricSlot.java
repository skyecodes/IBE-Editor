package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.Slot;
import com.github.franckyi.ibeeditor.mixin.SlotMixin;
import net.minecraft.entity.player.PlayerInventory;

public class FabricSlot implements Slot {
    private final net.minecraft.screen.slot.Slot slot;
    private Item stack;

    public FabricSlot(net.minecraft.screen.slot.Slot slot) {
        this.slot = slot;
    }

    @Override
    public boolean hasStack() {
        return slot != null && slot.hasStack();
    }

    @Override
    public boolean isInPlayerInventory() {
        return slot.inventory instanceof PlayerInventory;
    }

    @Override
    public int getIndex() {
        return ((SlotMixin) slot).getIndex();
    }

    @Override
    public Item getStack() {
        if (stack == null) {
            stack = new FabricItem(slot.getStack());
        }
        return stack;
    }
}
