package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.gui.GuiButtonView;
import net.minecraft.client.Minecraft;

public class Button extends Node {

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
    public GuiButtonView getView() {
        return (GuiButtonView) super.getView();
    }

    @Override
    protected void computeWidth() {
        this.setComputedWidth(Minecraft.getInstance().fontRenderer.getStringWidth(this.getText()) + this.getPadding().getVertical() + 10);
    }

    @Override
    protected void computeHeight() {
        this.setComputedHeight(20 + this.getPadding().getHorizontal());
    }

}
