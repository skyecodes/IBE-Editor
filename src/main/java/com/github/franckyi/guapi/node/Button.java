package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.Node;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class Button extends Node<Button.GuiButtonView> {

    public Button(String text) {
        super(new GuiButtonView());
        this.getView().displayString = text;
        this.computeSize();
        this.updateSize();
    }

    public Button() {
        this("Button");
    }

    public String getText() {
        return this.getView().displayString;
    }

    public void setText(String text) {
        this.getView().displayString = text;
        this.computeWidth();
        this.updateWidth();
    }

    public boolean isDisabled() {
        return !this.getView().enabled;
    }

    public void setDisabled(boolean disabled) {
        this.getView().enabled = !disabled;
    }

    @Override
    protected void computeWidth() {
        this.setComputedWidth(Minecraft.getInstance().fontRenderer.getStringWidth(this.getText()) + this.getPadding().getVertical() + 10);
    }

    @Override
    protected void computeHeight() {
        this.setComputedHeight(20 + this.getPadding().getHorizontal());
    }

    public static class GuiButtonView extends GuiButton implements Node.GuiView {

        public GuiButtonView() {
            super(0, 0, 0, "");
        }

        @Override
        public int getX() {
            return this.x;
        }

        @Override
        public void setX(int x) {
            this.x = x;
        }

        @Override
        public int getY() {
            return this.y;
        }

        @Override
        public void setY(int y) {
            this.y = y;
        }

        @Override
        public int getHeight() {
            return this.height;
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
}
