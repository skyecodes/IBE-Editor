package com.github.franckyi.guapi.scene;

import net.minecraft.client.gui.GuiScreen;

@FunctionalInterface
public interface Background {

    Background DEFAULT = GuiScreen::drawDefaultBackground;
    Background NONE = guiScreen -> {
    };

    static Background texturedBackground(int tint) {
        return guiScreen -> guiScreen.drawBackground(tint);
    }

    static Background worldBackground(int tint) {
        return guiScreen -> guiScreen.drawWorldBackground(tint);
    }

    void draw(GuiScreen screen);

}
