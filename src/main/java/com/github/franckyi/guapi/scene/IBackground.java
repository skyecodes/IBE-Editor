package com.github.franckyi.guapi.scene;

import net.minecraft.client.gui.GuiScreen;

@FunctionalInterface
public interface IBackground {

    IBackground DEFAULT = GuiScreen::drawDefaultBackground;
    IBackground NONE = guiScreen -> {
    };

    static IBackground texturedBackground(int tint) {
        return guiScreen -> guiScreen.drawBackground(tint);
    }

    static IBackground worldBackground(int tint) {
        return guiScreen -> guiScreen.drawWorldBackground(tint);
    }

    void draw(GuiScreen screen);

}
