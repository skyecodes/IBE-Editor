package com.github.franckyi.ibeeditor.impl.client.mvc.editor.controller.entry;

import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry.TextEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.entry.TextEntryView;
import com.github.franckyi.minecraft.api.common.text.PlainText;
import com.github.franckyi.minecraft.api.common.text.Text;

public class TextEntryController extends LabeledEntryController<TextEntryModel, TextEntryView> {
    public TextEntryController(TextEntryModel model, TextEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getTextField().setTextRenderer(this::renderTextField);
        view.getTextField().focusedProperty().addListener(value -> {
            if (value) {
                model.getCategory().getEditor().setFocusedTextEntry(this);
            } else if (model.getCategory().getEditor().getFocusedTextEntry() == this) {
                model.getCategory().getEditor().setFocusedTextEntry(null);
            }
        });
        model.validProperty().bind(view.getTextField().validProperty());
        view.getTextField().setText(model.getRawValue());
        model.rawValueProperty().bindBidirectional(view.getTextField().textProperty());
        if (model.getValue() instanceof PlainText) {
            view.switchConvertButton();
        }
        /*view.getConvertButton().onAction(() -> {
            if (model.getValue() instanceof PlainText) {
                model.setValue(model.getDefaultTranslatedValue());
            } else {
                model.setValue(new PlainTextImpl(model.getValue().getRawText()));
            }
        });*/
    }

    private Text renderTextField(String str, int firstCharacterIndex) {
        return model.getValue();
    }
}
