package com.github.franckyi.ibeeditor.impl.client.mvc.editor.controller;

import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.StringEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.StringEntryView;

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
