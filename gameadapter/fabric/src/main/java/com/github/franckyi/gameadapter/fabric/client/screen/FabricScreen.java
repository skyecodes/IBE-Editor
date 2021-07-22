package com.github.franckyi.gameadapter.fabric.client.screen;

import com.github.franckyi.gameadapter.api.client.screen.Screen;
import com.github.franckyi.gameadapter.api.common.Slot;
import com.github.franckyi.gameadapter.fabric.common.FabricSlot;
import com.github.franckyi.gameadapter.fabric.mixin.FabricHandledScreenMixin;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;

public class FabricScreen implements Screen {
    private final net.minecraft.client.gui.screen.Screen screen;

    public FabricScreen(net.minecraft.client.gui.screen.Screen screen) {
        this.screen = screen;
    }

    @Override
    public Slot getInventoryFocusedSlot() {
        return new FabricSlot(((FabricHandledScreenMixin) screen).getFocusedSlot());
    }

    @Override
    public boolean isCreativeInventoryScreen() {
        return screen instanceof CreativeInventoryScreen;
    }
}
