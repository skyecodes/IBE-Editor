package com.github.franckyi.guapi.scene;

import net.minecraft.client.gui.screen.Screen;

@FunctionalInterface
public interface IBackground {

    IBackground DEFAULT = Screen::renderBackground;
    IBackground NONE = guiScreen -> {
    };

    static IBackground texturedBackground(int tint) {
        return guiScreen -> guiScreen.renderDirtBackground(tint);
    }

    static IBackground worldBackground(int tint) {
        return guiScreen -> guiScreen.renderBackground(tint);
    }

    void draw(Screen screen);

}
