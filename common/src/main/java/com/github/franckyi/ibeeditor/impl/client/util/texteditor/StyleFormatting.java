package com.github.franckyi.ibeeditor.impl.client.util.texteditor;

import com.github.franckyi.minecraft.api.common.text.Text;

public class StyleFormatting extends Formatting {
    private StyleType type;

    public StyleFormatting(int start, int end, StyleType type) {
        super(start, end);
        this.type = type;
    }

    public StyleType getType() {
        return type;
    }

    public void setType(StyleType type) {
        this.type = type;
    }

    @Override
    public void apply(Text text) {
        switch (type) {
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

}
