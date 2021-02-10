package com.github.franckyi.gamehooks.impl.client;

import com.github.franckyi.gamehooks.api.client.Screen;
import com.github.franckyi.gamehooks.api.common.Slot;
import com.github.franckyi.gamehooks.impl.common.FabricSlot;
import com.github.franckyi.ibeeditor.mixin.HandledScreenMixin;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;

public class FabricScreen implements Screen {
    private final net.minecraft.client.gui.screen.Screen screen;

    public FabricScreen(net.minecraft.client.gui.screen.Screen screen) {
        this.screen = screen;
    }

    @Override
    public Slot getInventoryFocusedSlot() {
        return new FabricSlot(((HandledScreenMixin) screen).getFocusedSlot());
    }

    @Override
    public boolean isPlayerInventoryScreen() {
        return screen instanceof AbstractInventoryScreen;
    }
}
