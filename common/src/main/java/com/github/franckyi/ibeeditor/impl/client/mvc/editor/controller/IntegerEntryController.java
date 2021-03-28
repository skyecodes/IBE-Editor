package com.github.franckyi.ibeeditor.impl.client.mvc.editor.controller;

import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.IntegerEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.IntegerEntryView;

public class IntegerEntryController extends LabeledEntryController<IntegerEntryModel, IntegerEntryView> {
    public IntegerEntryController(IntegerEntryModel model, IntegerEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getTextField().textProperty().addListener(value -> {
            if (view.getTextField().isValid()) {
                model.setValue(Integer.parseInt(value));
            }
        });
        model.valueProperty().addListener(value -> view.getTextField().setText(Integer.toString(value)));
        view.getTextField().setText(Integer.toString(model.getValue()));
        model.validProperty().bind(view.getTextField().validProperty());
    }
}
