package com.github.franckyi.minecraft.impl.common.text;

import com.github.franckyi.minecraft.api.common.text.PlainText;

import java.util.Objects;

public abstract class AbstractPlainText extends AbstractText implements PlainText {
    private String text;

    protected AbstractPlainText() {
    }

    protected AbstractPlainText(String text) {
        this.text = text;
    }

    protected AbstractPlainText(PlainText text) {
        super(text);
        this.text = text.getText();
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        if (!Objects.equals(this.text, text)) {
            this.text = text;
            shouldUpdateComponent = true;
        }
    }
}
