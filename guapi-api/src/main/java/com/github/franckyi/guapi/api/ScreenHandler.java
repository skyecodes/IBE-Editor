package com.github.franckyi.guapi.api;

import com.github.franckyi.guapi.api.node.Scene;

public interface ScreenHandler {
    void showScene(Scene scene);

    void hideScene();

    Object getScreen();

    boolean mouseClicked(double mouseX, double mouseY, int button);

    boolean mouseReleased(double mouseX, double mouseY, int button);

    boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY);

    boolean mouseScrolled(double mouseX, double mouseY, double amount);

    boolean keyPressed(int keyCode, int scanCode, int modifiers);

    boolean keyReleased(int keyCode, int scanCode, int modifiers);

    boolean charTyped(char chr, int keyCode);

    void mouseMoved(double mouseX, double mouseY);
}

