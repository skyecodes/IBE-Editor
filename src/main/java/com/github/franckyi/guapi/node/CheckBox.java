package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.Node;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.config.GuiCheckBox;

public class CheckBox extends Node<CheckBox.GuiCheckBoxView> {

    public CheckBox() {
        this("", false);
    }

    public CheckBox(String text) {
        this(text, false);
    }

    public CheckBox(boolean checked) {
        this("", checked);
    }

    public CheckBox(String text, boolean checked) {
        super(new GuiCheckBoxView(text, checked));
    }

    public String getText() {
        return this.getView().displayString;
    }

    public void setText(String text) {
        this.getView().displayString = text;
    }

    public boolean isChecked() {
        return this.getView().isChecked();
    }

    public void setChecked(boolean checked) {
        this.getView().setIsChecked(checked);
    }

    public boolean isDisabled() {
        return !this.getView().enabled;
    }

    public void setDisabled(boolean disabled) {
        this.getView().enabled = !disabled;
    }

    @Override
    protected void computeWidth() {
        this.setComputedWidth(11 + 2 + Minecraft.getInstance().fontRenderer.getStringWidth(this.getText()));
    }

    @Override
    protected void computeHeight() {
        this.setComputedHeight(11);
    }

    static class GuiCheckBoxView extends GuiCheckBox implements Node.GuiView {

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
}
