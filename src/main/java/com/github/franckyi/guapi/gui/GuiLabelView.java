package com.github.franckyi.guapi.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiLabel;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuiLabelView extends GuiLabel implements GuiView {

    public GuiLabelView(String text, int color) {
        super(new ArrayList<>(Collections.singletonList(text)), color, Minecraft.getInstance().fontRenderer);
        this.setVisible(true);
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

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

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

    public void setColor(int color) {
        ObfuscationReflectionHelper.setPrivateValue(GuiLabel.class, this, color, "field_146168_n");
    }

    public void setCentered(boolean centered) {
        ObfuscationReflectionHelper.setPrivateValue(GuiLabel.class, this, centered, "field_146170_l");
    }

    @SuppressWarnings("unchecked")
    public void setText(String text) {
        ((List<String>) ObfuscationReflectionHelper.getPrivateValue(GuiLabel.class, this, "field_146173_k")).set(0, text);
    }

}
