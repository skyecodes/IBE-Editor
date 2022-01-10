package com.github.franckyi.ibeeditor.client.screen.controller.entry;

import com.github.franckyi.ibeeditor.client.screen.model.entry.IntegerEntryModel;
import com.github.franckyi.ibeeditor.client.screen.view.entry.IntegerEntryView;

public class IntegerEntryController<M extends IntegerEntryModel, V extends IntegerEntryView> extends ValueEntryController<M, V> {
    public IntegerEntryController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getTextField().textProperty().addListener(value -> {
            if (view.getTextField().isValid()) {
                model.setValue(Integer.parseInt(value));
                model.setValid(true);
            }
        });
        model.valueProperty().addListener(value -> view.getTextField().setText(Integer.toString(value)));
        view.getTextField().setText(Integer.toString(model.getValue()));
        view.getTextField().validProperty().addListener(model::setValid);
    }
}
