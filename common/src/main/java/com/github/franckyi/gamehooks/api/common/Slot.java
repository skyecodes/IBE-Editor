package com.github.franckyi.gamehooks.api.common;

public interface Slot {
    boolean hasStack();

    boolean isInPlayerInventory();

    int getId();

    Item getStack();
}
