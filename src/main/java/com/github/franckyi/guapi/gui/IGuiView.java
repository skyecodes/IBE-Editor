package com.github.franckyi.guapi.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.IGuiEventListener;

public interface IGuiView extends IGuiEventListener {

    int getViewX();

    void setViewX(int x);

    int getViewY();

    void setViewY(int y);

    int getViewWidth();

    void setViewWidth(int width);

    int getViewHeight();

    void setViewHeight(int height);

    boolean isViewVisible();

    void setViewVisible(boolean visible);

    void renderView(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks);

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
    default boolean mouseScrolled(double mouseX, double mouseY, double amount) {
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
    default boolean changeFocus(boolean focused) {
        return false;
    }

    default boolean inBounds(double x, double y) {
        return x >= this.getViewX() && x <= this.getViewX() + this.getViewWidth() &&
                y >= this.getViewY() && y <= this.getViewY() + this.getViewHeight();
    }

}
