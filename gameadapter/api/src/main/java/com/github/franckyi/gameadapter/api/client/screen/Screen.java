package com.github.franckyi.gameadapter.api.client.screen;

import com.github.franckyi.gameadapter.api.common.Slot;

public interface Screen {
    Slot getInventoryFocusedSlot();

    boolean isCreativeInventoryScreen();
}
