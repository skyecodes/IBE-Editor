package com.github.franckyi.gamehooks.api.common;

public interface Slot {
    boolean hasStack();

    boolean isInPlayerInventory();

    int getIndex();

    Item getStack();
}
