package com.github.franckyi.ibeeditor.impl.client.mvc.editor.controller.entry;

import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry.StringEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.entry.StringEntryView;

public class StringEntryController extends LabeledEntryController<StringEntryModel, StringEntryView> {
    public StringEntryController(StringEntryModel model, StringEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getTextField().setText(model.getValue());
        view.getTextField().textProperty().bindBidirectional(model.valueProperty());
        model.validProperty().bind(view.getTextField().validProperty());
    }
}
