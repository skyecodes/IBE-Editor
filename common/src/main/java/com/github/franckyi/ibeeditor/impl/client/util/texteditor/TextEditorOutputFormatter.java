package com.github.franckyi.ibeeditor.impl.client.util.texteditor;

import com.github.franckyi.minecraft.api.common.text.PlainText;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.List;
import java.util.stream.Collectors;

public class TextEditorOutputFormatter {
    private final PlainText rootText;
    private int currentFormattingIndex, currentTextIndex, previousTextIndex;
    private List<Formatting> currentFormattings;

    public TextEditorOutputFormatter(PlainText rootText) {
        this.rootText = rootText;
    }

    public void format(String text, int firstCharacterIndex, List<Formatting> formattings) {
        initFormattingsForIndex(formattings, firstCharacterIndex);
        for (currentTextIndex = 1; currentTextIndex <= text.length(); currentTextIndex++) {
            currentFormattingIndex++;
            List<Formatting> changedFormattings = getChangedFormattingsForCurrentIndex(formattings);
            if (changedFormattings.isEmpty()) {
                continue;
            }
            appendText(text.substring(previousTextIndex, currentTextIndex));
            changedFormattings.forEach(formatting -> {
                if (formatting.getStart() == currentFormattingIndex) {
                    currentFormattings.add(formatting);
                } else {
                    currentFormattings.remove(formatting);
                }
            });
            previousTextIndex = currentTextIndex;
        }
        if (previousTextIndex != currentTextIndex - 1) {
            appendText(text.substring(previousTextIndex, currentTextIndex - 1));
        }
    }

    private void initFormattingsForIndex(List<Formatting> formattings, int index) {
        currentFormattingIndex = index;
        currentFormattings = formattings.stream()
                .filter(formatting -> formatting.getStart() <= currentFormattingIndex && formatting.getEnd() > currentFormattingIndex)
                .collect(Collectors.toList());
    }

    private List<Formatting> getChangedFormattingsForCurrentIndex(List<Formatting> formattings) {
        return formattings.stream()
                .filter(formatting -> formatting.getStart() == currentFormattingIndex || formatting.getEnd() == currentFormattingIndex)
                .collect(Collectors.toList());
    }

    private void appendText(String s) {
        PlainText text = Text.createPlainText(s);
        currentFormattings.forEach(formatting -> formatting.apply(text));
        rootText.addExtra(text);
    }

    public PlainText getText() {
        return rootText;
    }
}
