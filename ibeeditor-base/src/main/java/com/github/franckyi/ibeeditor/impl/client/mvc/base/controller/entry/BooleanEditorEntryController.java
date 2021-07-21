package com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.entry;

import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.BooleanEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry.BooleanEditorEntryView;

public class BooleanEditorEntryController extends ValueEditorEntryController<BooleanEditorEntryModel, BooleanEditorEntryView> {
    public BooleanEditorEntryController(BooleanEditorEntryModel model, BooleanEditorEntryView view) {
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
