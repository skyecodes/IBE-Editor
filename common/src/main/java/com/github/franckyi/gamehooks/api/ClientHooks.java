package com.github.franckyi.gamehooks.api;

import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.Renderer;
import com.github.franckyi.gamehooks.api.client.ScreenScaling;
import com.github.franckyi.gamehooks.api.common.Player;
import com.github.franckyi.gamehooks.api.common.Pos;

public interface ClientHooks {
    <M, T> Renderer<M, T> renderer();

    ScreenScaling screen();

    KeyBinding registerKeyBinding(String name, int keyCode, String category);

    Player player();

    int entityIdMouseOver();

    Pos blockPosMouseOver();

    @Deprecated
    void unlockCursor();
}
