package com.github.franckyi.ibeeditor.base.client.util.texteditor;

import com.github.franckyi.gameadapter.api.common.text.Text;

import java.util.Objects;

public class StyleFormatting extends Formatting {
    private StyleType target;

    public StyleFormatting(int start, int end, StyleType target) {
        super(start, end);
        this.target = target;
    }

    public StyleType getType() {
        return target;
    }

    public void setType(StyleType target) {
        this.target = target;
    }

    @Override
    public void apply(Text text) {
        switch (target) {
            case BOLD:
                text.setBold(true);
                break;
            case ITALIC:
                text.setItalic(true);
                break;
            case UNDERLINED:
                text.setUnderlined(true);
                break;
            case STRIKETHROUGH:
                text.setStrikethrough(true);
                break;
            case OBFUSCATED:
                text.setObfuscated(true);
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StyleFormatting that = (StyleFormatting) o;
        return target == that.target;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), target);
    }
}
