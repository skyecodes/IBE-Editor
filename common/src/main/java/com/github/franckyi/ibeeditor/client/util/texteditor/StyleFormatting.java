package com.github.franckyi.ibeeditor.client.util.texteditor;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;

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
    public void apply(MutableComponent text) {
        switch (target) {
            case BOLD:
                text.withStyle(ChatFormatting.BOLD);
                break;
            case ITALIC:
                text.withStyle(ChatFormatting.ITALIC);
                break;
            case UNDERLINED:
                text.withStyle(ChatFormatting.UNDERLINE);
                break;
            case STRIKETHROUGH:
                text.withStyle(ChatFormatting.STRIKETHROUGH);
                break;
            case OBFUSCATED:
                text.withStyle(ChatFormatting.OBFUSCATED);
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
