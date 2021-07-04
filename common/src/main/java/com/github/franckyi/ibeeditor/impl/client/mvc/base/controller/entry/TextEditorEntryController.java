package com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.entry;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.view.StandardEditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.TextEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry.TextEditorEntryView;
import com.github.franckyi.minecraft.api.common.text.PlainText;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.text.TranslatedText;
import com.github.franckyi.minecraft.api.common.text.builder.PlainTextBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class TextEditorEntryController extends LabeledEditorEntryController<TextEditorEntryModel, TextEditorEntryView> {
    private boolean hasTextChanged;
    private final List<Formatting> formattings = new ArrayList<>();

    public TextEditorEntryController(TextEditorEntryModel model, TextEditorEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getTextField().setTextRenderer(this::renderText);
        view.getTextField().focusedProperty().addListener(this::onTextFieldFocus);
        view.getTextField().setText(model.getRawValue());
        view.getTextField().textProperty().addListener(this::onTextChanged);
        view.getResetButton().onAction(this::resetText);
        model.validProperty().bind(view.getTextField().validProperty());
        model.rawValueProperty().bindBidirectional(view.getTextField().textProperty());
        model.valueProperty().addListener(this::updateResetButton);
        updateResetButton();
    }

    private Text renderText(String str, int firstCharacterIndex) {
        if (!str.isEmpty()) {
            TextFormatter formatter = new TextFormatter(Text.createPlainText(null));
            formatter.format(str, firstCharacterIndex, formattings);
            return formatter.getText();
        }
        return Text.EMPTY;
    }

    private void updateResetButton() {
        view.getResetButton().setDisable(model.getValue() instanceof TranslatedText);
    }

    private void onTextFieldFocus(boolean focused) {
        if (focused) {
            model.getCategory().getEditor().setFocusedTextEntry(this);
        } else if (model.getCategory().getEditor().getFocusedTextEntry() == this) {
            model.getCategory().getEditor().setFocusedTextEntry(null);
        }
    }

    private void onTextChanged(String newVal) {
        if (hasTextChanged) {
            model.setValue(text(newVal));
        } else {
            model.initValue(text(newVal));
            hasTextChanged = true;
        }
    }

    private void resetText() {
        model.resetValue();
        hasTextChanged = false;
    }

    public void addFormatting(StandardEditorView.TextButtonType textButtonType) {

    }

    public static class TextFormatter {
        private final Text rootText;
        private int currentFormattingIndex, currentTextIndex, previousTextIndex;
        private List<Formatting> currentFormattings;

        public TextFormatter(Text rootText) {
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
            PlainTextBuilder text = Text.createPlainText(s);
            currentFormattings.forEach(formatting -> formatting.apply(text));
            if (rootText.getExtra() == null) {
                rootText.setExtra(new ArrayList<>());
            }
            rootText.getExtra().add(text);
        }

        public Text getText() {
            return rootText;
        }
    }

    abstract static class Formatting {
        private final int start, end;

        public Formatting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public abstract void apply(Text text);
    }

    static class ColorFormatting extends Formatting {
        private final String color;

        public ColorFormatting(int start, int end, String color) {
            super(start, end);
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        @Override
        public void apply(Text text) {
            text.setColor(color);
        }
    }

    static class StyleFormatting extends Formatting {
        private final Type type;

        public StyleFormatting(int start, int end, Type type) {
            super(start, end);
            this.type = type;
        }

        public Type getType() {
            return type;
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

        enum Type {
            BOLD, ITALIC, UNDERLINED, STRIKETHROUGH, OBFUSCATED
        }
    }
}
