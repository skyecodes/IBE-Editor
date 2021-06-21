package com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.entry;

import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.StringEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry.StringEditorEntryView;

public class StringEditorEntryController extends LabeledEditorEntryController<StringEditorEntryModel, StringEditorEntryView> {
    public StringEditorEntryController(StringEditorEntryModel model, StringEditorEntryView view) {
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
