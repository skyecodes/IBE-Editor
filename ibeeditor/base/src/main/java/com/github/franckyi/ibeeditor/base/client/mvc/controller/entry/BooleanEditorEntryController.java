package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.BooleanEditorEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.BooleanEditorEntryView;

public class BooleanEditorEntryController<M extends BooleanEditorEntryModel, V extends BooleanEditorEntryView> extends ValueEditorEntryController<M, V> {
    public BooleanEditorEntryController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getLabel().labelProperty().bind(model.labelProperty());
        view.getCheckBox().setChecked(model.getValue());
        view.getCheckBox().checkedProperty().bindBidirectional(model.valueProperty());
    }
}
