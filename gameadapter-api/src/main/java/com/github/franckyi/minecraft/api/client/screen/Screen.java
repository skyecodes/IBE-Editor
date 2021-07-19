package com.github.franckyi.minecraft.api.client.screen;

import com.github.franckyi.minecraft.api.common.Slot;

public interface Screen {
    Slot getInventoryFocusedSlot();

    boolean isCreativeInventoryScreen();
}
