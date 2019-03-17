package com.github.franckyi.guapi.gui;

import net.minecraftforge.fml.client.config.GuiCheckBox;

public class GuiCheckBoxView extends GuiCheckBox implements GuiView {

    public GuiCheckBoxView(String displayString, boolean isChecked) {
        super(0, 0, 0, displayString, isChecked);
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
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
