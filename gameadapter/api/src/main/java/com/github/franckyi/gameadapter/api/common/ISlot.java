package com.github.franckyi.gameadapter.api.common;

public interface ISlot {
    boolean hasStack();

    boolean isInPlayerInventory();

    int getIndex();

    IItemStack getStack();
}
