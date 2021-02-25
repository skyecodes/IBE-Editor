package com.github.franckyi.gamehooks.api.client;

import com.github.franckyi.gamehooks.api.common.Slot;

public interface Screen {
    Slot getInventoryFocusedSlot();

    boolean isCreativeInventoryScreen();
}
