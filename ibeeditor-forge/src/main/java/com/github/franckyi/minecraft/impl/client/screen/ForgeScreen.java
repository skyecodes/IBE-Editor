package com.github.franckyi.minecraft.impl.client.screen;

import com.github.franckyi.minecraft.api.client.screen.Screen;
import com.github.franckyi.minecraft.api.common.Slot;
import com.github.franckyi.minecraft.impl.common.ForgeSlot;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;

public class ForgeScreen implements Screen {
    private final net.minecraft.client.gui.screen.Screen screen;

    public ForgeScreen(net.minecraft.client.gui.screen.Screen screen) {
        this.screen = screen;
    }

    @Override
    public Slot getInventoryFocusedSlot() {
        return new ForgeSlot(((ContainerScreen<?>) screen).getSlotUnderMouse());
    }

    @Override
    public boolean isCreativeInventoryScreen() {
        return screen instanceof CreativeScreen;
    }
}
