package com.github.franckyi.gameadapter.api;

import com.github.franckyi.gameadapter.api.client.KeyBinding;
import com.github.franckyi.gameadapter.api.client.render.Matrices;
import com.github.franckyi.gameadapter.api.client.render.Renderer;
import com.github.franckyi.gameadapter.api.client.screen.Screen;
import com.github.franckyi.gameadapter.api.client.screen.ScreenScaling;
import com.github.franckyi.gameadapter.api.common.world.Player;
import com.github.franckyi.gameadapter.api.common.world.World;
import com.github.franckyi.gameadapter.api.common.world.WorldBlock;
import com.github.franckyi.gameadapter.api.common.world.WorldEntity;

public interface GameClient {
    Renderer getRenderer();

    ScreenScaling getScreenScaling();

    KeyBinding registerKeyBinding(String name, int keyCode, String category);

    Player getPlayer();

    World getWorld();

    WorldEntity getEntityMouseOver();

    WorldBlock getBlockMouseOver();

    <M> MatricesFactory<M> getMatricesFactory();

    <S> ScreenFactory<S> getScreenFactory();

    Object getEffectSprite(String effectId);

    @Deprecated
    void unlockCursor();

    interface MatricesFactory<M> {
        Matrices createMatrices(M matrices);
    }

    interface ScreenFactory<S> {
        Screen createScreen(S screen);
    }
}
