package com.github.franckyi.gameadapter.api.client;

import com.github.franckyi.gameadapter.api.common.ISlot;

public interface IScreen {
    ISlot getInventoryFocusedSlot();

    boolean isCreativeInventoryScreen();
}
