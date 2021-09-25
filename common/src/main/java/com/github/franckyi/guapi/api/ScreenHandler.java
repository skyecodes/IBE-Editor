package com.github.franckyi.guapi.api;

import com.github.franckyi.guapi.api.node.Scene;
import net.minecraft.client.gui.screens.Screen;

public interface ScreenHandler {
    void showScene(Scene scene);

    void hideScene();

    Screen getGuapiScreen();
}
