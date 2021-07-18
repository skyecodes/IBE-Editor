package com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.entry;

import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry.LabeledEditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry.LabeledEditorEntryView;

public abstract class LabeledEditorEntryController<M extends LabeledEditorEntryModel, V extends LabeledEditorEntryView> extends AbstractEditorEntryController<M, V> {
    public LabeledEditorEntryController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getLabel().labelProperty().bind(model.labelProperty());
    }
}
