package com.github.franckyi.guapi.gui;

import com.github.franckyi.guapi.Node;
import net.minecraft.client.gui.Gui;

public abstract class AbstractGuiView extends Gui implements Node.GuiView {

    private int x, y, width, height;
    private boolean visible;

    public AbstractGuiView() {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        visible = true;
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
    public int getWidth() {
        return width;
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
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
