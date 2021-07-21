package com.github.franckyi.gameadapter.base.common.text;

import com.github.franckyi.gameadapter.api.common.text.PlainText;

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

    @Override
    public String getRawText() {
        return (text == null ? "" : text) + super.getRawText();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AbstractPlainText that = (AbstractPlainText) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text);
    }
}
