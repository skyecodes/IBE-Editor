package com.github.franckyi.minecraft.api.common;

import com.github.franckyi.minecraft.api.common.world.Item;

public interface Slot {
    boolean hasStack();

    boolean isInPlayerInventory();

    int getIndex();

    Item getStack();
}
