package com.github.franckyi.guapi.gui;

import net.minecraft.client.gui.IGuiEventListener;

public interface GuiView extends IGuiEventListener {

    int getX();

    void setX(int x);

    int getY();

    void setY(int y);

    int getWidth();

    void setWidth(int width);

    int getHeight();

    void setHeight(int height);

    boolean isVisible();

    void setVisible(boolean visible);

    void render(int mouseX, int mouseY, float partialTicks);

    @Override
    default boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        return this.inBounds(mouseX, mouseY);
    }

    @Override
    default boolean mouseReleased(double mouseX, double mouseY, int mouseButton) {
        return this.inBounds(mouseX, mouseY);
    }

    @Override
    default boolean mouseDragged(double mouseX, double mouseY, int mouseButton, double deltaX, double deltaY) {
        return this.inBounds(mouseX, mouseY);
    }

    @Override
    default boolean mouseScrolled(double amount) {
        return false;
    }

    @Override
    default boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return false;
    }

    @Override
    default boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return false;
    }

    @Override
    default boolean charTyped(char charTyped, int modifiers) {
        return false;
    }

    @Override
    default void focusChanged(boolean focused) {

    }

    @Override
    default boolean canFocus() {
        return false;
    }

    default boolean inBounds(double x, double y) {
        return x >= this.getX() && x <= this.getX() + this.getWidth() &&
                y >= this.getY() && y <= this.getY() + this.getHeight();
    }

}
