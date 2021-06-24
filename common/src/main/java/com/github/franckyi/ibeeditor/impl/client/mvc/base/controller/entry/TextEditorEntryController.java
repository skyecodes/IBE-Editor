package com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.entry;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.view.StandardEditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.TextEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry.TextEditorEntryView;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.text.TranslatedText;
import org.apache.logging.log4j.LogManager;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class TextEditorEntryController extends LabeledEditorEntryController<TextEditorEntryModel, TextEditorEntryView> {
    private boolean hasTextChanged;

    public TextEditorEntryController(TextEditorEntryModel model, TextEditorEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getTextField().setTextRenderer(this::renderTextField);
        view.getTextField().focusedProperty().addListener(this::onTextFieldFocus);
        view.getTextField().setText(model.getRawValue());
        view.getTextField().textProperty().addListener(this::onTextChanged);
        view.getResetButton().onAction(this::resetText);
        model.validProperty().bind(view.getTextField().validProperty());
        model.rawValueProperty().bindBidirectional(view.getTextField().textProperty());
        model.valueProperty().addListener(this::updateResetButton);
        updateResetButton();
        //temp
        view.getTextField().cursorPositionProperty().addListener(value -> LogManager.getLogger().debug("Cursor = {}", value));
        view.getTextField().highlightPositionProperty().addListener(value -> LogManager.getLogger().debug("Highlight = {}", value));
    }

    private Text renderTextField(String str, int firstCharacterIndex) {
        if (!str.isEmpty()) {
            return model.getValue();
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
            model.setValue(text(newVal));
            hasTextChanged = true;
        }
    }

    private void resetText() {
        model.resetValue();
        hasTextChanged = false;
    }

    public void addFormatting(StandardEditorView.TextButtonType textButtonType) {
        view.getTextField().getCursorPosition();
    }
}
