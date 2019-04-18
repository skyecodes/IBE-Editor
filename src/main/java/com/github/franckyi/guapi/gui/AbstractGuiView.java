package com.github.franckyi.guapi.gui;

import net.minecraft.client.gui.Gui;

public abstract class AbstractGuiView extends Gui implements GuiView {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;

    public AbstractGuiView() {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        visible = true;
    }

    @Override
    public int getViewX() {
        return x;
    }

    @Override
    public void setViewX(int x) {
        this.x = x;
    }

    @Override
    public int getViewY() {
        return y;
    }

    @Override
    public void setViewY(int y) {
        this.y = y;
    }

    @Override
    public int getViewWidth() {
        return width;
    }

    @Override
    public void setViewWidth(int width) {
        this.width = width;
    }

    @Override
    public int getViewHeight() {
        return height;
    }

    @Override
    public void setViewHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean isViewVisible() {
        return visible;
    }

    @Override
    public void setViewVisible(boolean visible) {
        this.visible = visible;
    }

}
