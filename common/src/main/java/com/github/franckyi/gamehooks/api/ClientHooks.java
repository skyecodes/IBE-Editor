package com.github.franckyi.gamehooks.api;

import com.github.franckyi.gamehooks.api.client.ClientPlayer;
import com.github.franckyi.gamehooks.api.client.FontRenderer;
import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.ShapeRenderer;
import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.api.common.World;

public interface ClientHooks {
    <M, T> FontRenderer<M, T> fontRenderer();

    <M> ShapeRenderer<M> shapeRenderer();

    KeyBinding registerKeyBinding(String name, int keyCode, String category);

    ClientPlayer player();

    World world();

    Entity entityMouseOver();

    Block blockMouseOver();

    @Deprecated
    void unlockCursor();
}
