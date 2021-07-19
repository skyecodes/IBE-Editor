package com.github.franckyi.minecraft.impl.client.screen;

import com.github.franckyi.ibeeditor.mixin.FabricHandledScreenMixin;
import com.github.franckyi.minecraft.api.client.screen.Screen;
import com.github.franckyi.minecraft.api.common.Slot;
import com.github.franckyi.minecraft.impl.common.FabricSlot;
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
