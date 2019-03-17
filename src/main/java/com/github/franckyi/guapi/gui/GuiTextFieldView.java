package com.github.franckyi.guapi.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;

public class GuiTextFieldView extends GuiTextField implements GuiView {

    public GuiTextFieldView() {
        super(0, Minecraft.getInstance().fontRenderer, 0, 0, 0, 0);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean isVisible() {
        return this.getVisible();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.drawTextField(mouseX, mouseY, partialTicks);
    }
}
