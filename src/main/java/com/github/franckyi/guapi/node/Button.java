package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.gui.GuiView;
import net.minecraft.client.gui.GuiButton;

public class Button extends Node<Button.GuiButtonView> {

    protected Button(GuiButtonView view) {
        super(view);
        this.computeSize();
        this.updateSize();
    }

    public Button(String text) {
        this(new GuiButtonView(text));
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
        this.setComputedWidth(mc.fontRenderer.getStringWidth(this.getText()) + this.getPadding().getVertical() + 10);
    }

    @Override
    protected void computeHeight() {
        this.setComputedHeight(20 + this.getPadding().getHorizontal());
    }

    public static class GuiButtonView extends GuiButton implements GuiView {

        public GuiButtonView(String text) {
            super(0, 0, 0, text);
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
