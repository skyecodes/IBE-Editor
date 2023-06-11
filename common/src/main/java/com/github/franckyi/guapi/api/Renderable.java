package com.github.franckyi.guapi.api;

import net.minecraft.client.gui.GuiGraphics;

public interface Renderable {
    default boolean preRender(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        return false;
    }

    void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta);

    default void postRender(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
    }
}
