package com.github.franckyi.gamehooks.api.common;

public interface Slot {
    boolean hasStack();

    boolean isPlayerInventory();

    int getId();

    Item getStack();
}
