package com.github.franckyi.gameadapter.api.common;

import com.github.franckyi.gameadapter.api.common.world.Item;

public interface Slot {
    boolean hasStack();

    boolean isInPlayerInventory();

    int getIndex();

    Item getStack();
}
