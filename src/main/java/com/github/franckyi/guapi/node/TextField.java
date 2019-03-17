package com.github.franckyi.guapi.node;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.gui.GuiTextFieldView;

public class TextField extends Node {

    public TextField() {
        this("");
    }

    public TextField(String text) {
        super(new GuiTextFieldView());
        this.setText(text);
        this.computeSize();
        this.updateSize();
    }

    @Override
    public GuiTextFieldView getView() {
        return (GuiTextFieldView) super.getView();
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
}
