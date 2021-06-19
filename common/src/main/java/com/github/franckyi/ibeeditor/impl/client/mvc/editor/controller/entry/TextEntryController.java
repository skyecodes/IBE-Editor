package com.github.franckyi.ibeeditor.impl.client.mvc.editor.controller.entry;

import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry.TextEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.entry.TextEntryView;
import com.github.franckyi.minecraft.api.common.text.PlainText;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.text.TranslatedText;

public class TextEntryController extends LabeledEntryController<TextEntryModel, TextEntryView> {
    public TextEntryController(TextEntryModel model, TextEntryView view) {
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
