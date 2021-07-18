package com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.entry;

import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.IntegerEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry.IntegerEditorEntryView;

public class IntegerEditorEntryController extends ValueEditorEntryController<IntegerEditorEntryModel, IntegerEditorEntryView> {
    public IntegerEditorEntryController(IntegerEditorEntryModel model, IntegerEditorEntryView view) {
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
