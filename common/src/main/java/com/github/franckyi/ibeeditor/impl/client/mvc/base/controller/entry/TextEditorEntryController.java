package com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.entry;

import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.TextEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry.TextEditorEntryView;
import com.github.franckyi.ibeeditor.impl.client.util.texteditor.*;
import com.github.franckyi.minecraft.api.common.text.PlainText;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.text.TranslatedText;

import java.util.List;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class TextEditorEntryController extends LabeledEditorEntryController<TextEditorEntryModel, TextEditorEntryView> implements TextEditorActionHandler {
    private List<Formatting> formattings;

    public TextEditorEntryController(TextEditorEntryModel model, TextEditorEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getTextField().setText(model.getValue().getRawText());
        view.getTextField().setTextRenderer(this::renderText);
        view.getTextField().focusedProperty().addListener(this::onTextFieldFocus);
        model.validProperty().bind(view.getTextField().validProperty());
        model.valueProperty().addListener(this::updateResetButton);
        model.setValueFactory(this::createText);
        initFormattings(model.getValue());
        updateResetButton();
    }

    private Text renderText(String str, int firstCharacterIndex) {
        if (!str.isEmpty()) {
            TextEditorOutputFormatter formatter = new TextEditorOutputFormatter(text().extra(text("")));
            formatter.format(str, firstCharacterIndex, formattings);
            return formatter.getText();
        }
        return Text.EMPTY;
    }

    private PlainText createText() {
        TextEditorOutputFormatter formatter = new TextEditorOutputFormatter(text());
        formatter.format(view.getTextField().getText(), 0, formattings);
        return formatter.getText();
    }

    private void initFormattings(PlainText text) {
        TextEditorInputParser parser = new TextEditorInputParser();
        parser.parse(text);
        formattings = parser.getFormattings();
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

    @Override
    public void addColorFormatting(String color) {
        addFormatting(color, ColorFormatting::new);
    }

    @Override
    public void addStyleFormatting(StyleType type) {
        addFormatting(type, StyleFormatting::new);
    }

    private <T> void addFormatting(T value, FormattingSupplier<T> supplier) {
        int start = view.getTextField().getCursorPosition();
        int end = view.getTextField().getHighlightPosition();
        if (start != end) {
            if (start < end) {
                formattings.add(supplier.get(start, end, value));
            } else {
                formattings.add(supplier.get(end, start, value));
            }
        }
    }

    @FunctionalInterface
    private interface FormattingSupplier<T> {
        Formatting get(int start, int end, T value);
    }
}
