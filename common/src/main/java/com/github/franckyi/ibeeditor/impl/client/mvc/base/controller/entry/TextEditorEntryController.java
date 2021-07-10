package com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.entry;

import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.TextEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry.TextEditorEntryView;
import com.github.franckyi.ibeeditor.impl.client.util.texteditor.*;
import com.github.franckyi.minecraft.api.common.text.PlainText;
import com.github.franckyi.minecraft.api.common.text.Text;

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
        model.setValueFactory(this::createText);
        initFormattings(model.getValue());
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

    private void onTextFieldFocus(boolean focused) {
        if (focused) {
            model.getCategory().getEditor().setActiveTextEditor(this);
        } else if (model.getCategory().getEditor().getActiveTextEditor() == this) {
            model.getCategory().getEditor().setActiveTextEditor(null);
        }
    }

    @Override
    public void addColorFormatting(String color) {
        int start = Math.min(view.getTextField().getCursorPosition(), view.getTextField().getHighlightPosition());
        int end = Math.max(view.getTextField().getCursorPosition(), view.getTextField().getHighlightPosition());
        if (start != end) {
            formattings.add(new ColorFormatting(start, end, color));
        }
    }

    @Override
    public void addStyleFormatting(StyleType type) {
        int start = Math.min(view.getTextField().getCursorPosition(), view.getTextField().getHighlightPosition());
        int end = Math.max(view.getTextField().getCursorPosition(), view.getTextField().getHighlightPosition());
        if (start != end) {
            formattings.add(new StyleFormatting(start, end, type));
        }
    }
}
