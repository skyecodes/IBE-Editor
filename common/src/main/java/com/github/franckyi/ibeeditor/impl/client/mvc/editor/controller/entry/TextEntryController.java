package com.github.franckyi.ibeeditor.impl.client.mvc.editor.controller.entry;

import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry.TextEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.entry.TextEntryView;

public class TextEntryController extends LabeledEntryController<TextEntryModel, TextEntryView> {
    public TextEntryController(TextEntryModel model, TextEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getTextField().setTextRenderer((s, i) -> model.getValue());
        view.getTextField().focusedProperty().addListener(value -> {
            if (value) {
                model.getCategory().getEditor().setFocusedTextEntry(model);
            } else if (model.getCategory().getEditor().getFocusedTextEntry() == model) {
                model.getCategory().getEditor().setFocusedTextEntry(null);
            }
        });
        model.validProperty().bind(view.getTextField().validProperty());
    }
}
