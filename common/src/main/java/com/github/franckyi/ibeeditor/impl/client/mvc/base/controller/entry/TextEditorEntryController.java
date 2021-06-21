package com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.entry;

import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.TextEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry.TextEditorEntryView;
import com.github.franckyi.minecraft.api.common.text.PlainText;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.text.TranslatedText;

public class TextEditorEntryController extends LabeledEditorEntryController<TextEditorEntryModel, TextEditorEntryView> {
    public TextEditorEntryController(TextEditorEntryModel model, TextEditorEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getTextField().setTextRenderer(this::renderTextField);
        view.getTextField().focusedProperty().addListener(this::onTextFieldFocus);
        view.getTextField().setText(model.getRawValue());
        model.validProperty().bind(view.getTextField().validProperty());
        model.rawValueProperty().bindBidirectional(view.getTextField().textProperty());
        model.valueProperty().addListener(this::updateResetButton);
        if (model.getValue() instanceof PlainText) {

        } else {
            view.getResetButton().setDisable(true);
        }
        updateResetButton();
    }

    private void updateResetButton() {
        view.getResetButton().setDisable(model.getValue() instanceof TranslatedText);
    }

    private Text renderTextField(String str, int firstCharacterIndex) {
        return model.getValue();
    }

    private void onTextFieldFocus(boolean focused) {
        if (focused) {
            model.getCategory().getEditor().setFocusedTextEntry(this);
        } else if (model.getCategory().getEditor().getFocusedTextEntry() == this) {
            model.getCategory().getEditor().setFocusedTextEntry(null);
        }
    }
}
