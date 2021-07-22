package com.github.franckyi.ibeeditor.base.client.util.texteditor;

import com.github.franckyi.gameadapter.api.common.text.PlainText;

import java.util.ArrayList;
import java.util.List;

public class TextEditorInputParser {
    private final List<Formatting> formattings = new ArrayList<>();
    private int index;

    public void parse(PlainText text) {
        int length = text.getRawText().length();
        if (text.isBold()) {
            formattings.add(new StyleFormatting(index, index + length, StyleType.BOLD));
        }
        if (text.isItalic()) {
            formattings.add(new StyleFormatting(index, index + length, StyleType.ITALIC));
        }
        if (text.isUnderlined()) {
            formattings.add(new StyleFormatting(index, index + length, StyleType.UNDERLINED));
        }
        if (text.isStrikethrough()) {
            formattings.add(new StyleFormatting(index, index + length, StyleType.STRIKETHROUGH));
        }
        if (text.isObfuscated()) {
            formattings.add(new StyleFormatting(index, index + length, StyleType.OBFUSCATED));
        }
        if (text.getColor() != null) {
            formattings.add(new ColorFormatting(index, index + length, text.getColor()));
        }
        if (text.getText() != null) {
            index += text.getText().length();
        }
        if (text.getExtra() != null) {
            text.getExtra().stream()
                    .filter(PlainText.class::isInstance)
                    .map(PlainText.class::cast)
                    .forEach(this::parse);
        }
    }

    public List<Formatting> getFormattings() {
        return formattings;
    }
}
