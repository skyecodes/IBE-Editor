package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.Screen;
import com.github.franckyi.gamehooks.api.common.Slot;
import com.github.franckyi.gamehooks.impl.common.ForgeSlot;
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
