package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.gui.GuiCheckBoxView;
import net.minecraft.client.Minecraft;

public class CheckBox extends Node {

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
    public GuiCheckBoxView getView() {
        return (GuiCheckBoxView) super.getView();
    }

    @Override
    protected void computeWidth() {
        this.setComputedWidth(11 + 2 + Minecraft.getInstance().fontRenderer.getStringWidth(this.getText()));
    }

    @Override
    protected void computeHeight() {
        this.setComputedHeight(11);
    }
}
