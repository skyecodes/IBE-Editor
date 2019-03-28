package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.Node;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;

public class TextField extends Node<TextField.GuiTextFieldView> {

    public TextField() {
        this("");
    }

    public TextField(String text) {
        super(new GuiTextFieldView());
        this.setText(text);
        this.computeSize();
        this.updateSize();
    }

    public String getText() {
        return this.getView().getText();
    }

    public void setText(String text) {
        this.getView().setText(text);
    }

    @Override
    protected void computeWidth() {
        this.setComputedWidth(100 + this.getPadding().getHorizontal());
    }

    @Override
    protected void computeHeight() {
        this.setComputedHeight(20 + this.getPadding().getVertical());
    }

    public void tick() {
        this.getView().tick();
    }

    static class GuiTextFieldView extends GuiTextField implements Node.GuiView {

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
}
