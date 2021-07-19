package com.github.franckyi.minecraft.api;

import com.github.franckyi.minecraft.api.client.KeyBinding;
import com.github.franckyi.minecraft.api.client.render.Renderer;
import com.github.franckyi.minecraft.api.client.screen.ScreenScaling;
import com.github.franckyi.minecraft.api.common.world.Player;
import com.github.franckyi.minecraft.api.common.world.World;
import com.github.franckyi.minecraft.api.common.world.WorldBlock;
import com.github.franckyi.minecraft.api.common.world.WorldEntity;

public interface MinecraftClient {
    Renderer getRenderer();

    ScreenScaling getScreenScaling();

    KeyBinding registerKeyBinding(String name, int keyCode, String category);

    Player getPlayer();

    World getWorld();

    WorldEntity getEntityMouseOver();

    WorldBlock getBlockMouseOver();

    @Deprecated
    void unlockCursor();
}
