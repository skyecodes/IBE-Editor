package com.github.franckyi.gamehooks.api;

import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.Renderer;
import com.github.franckyi.gamehooks.api.client.ScreenHandler;
import com.github.franckyi.gamehooks.api.client.ScreenScaling;
import com.github.franckyi.gamehooks.api.common.Player;
import com.github.franckyi.gamehooks.api.common.World;
import com.github.franckyi.gamehooks.api.common.WorldBlock;
import com.github.franckyi.gamehooks.api.common.WorldEntity;

public interface ClientHooks {
    Renderer getRenderer();

    ScreenHandler getScreenHandler();

    ScreenScaling getScreenScaling();

    KeyBinding registerKeyBinding(String name, int keyCode, String category);

    Player getPlayer();

    World getWorld();

    WorldEntity getEntityMouseOver();

    WorldBlock getBlockMouseOver();

    @Deprecated
    void unlockCursor();
}
