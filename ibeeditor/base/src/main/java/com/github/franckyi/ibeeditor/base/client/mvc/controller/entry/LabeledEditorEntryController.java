package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.LabeledEditorEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.LabeledEditorEntryView;

public abstract class LabeledEditorEntryController<M extends LabeledEditorEntryModel, V extends LabeledEditorEntryView> extends EditorEntryController<M, V> {
    public LabeledEditorEntryController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getLabel().labelProperty().bind(model.labelProperty());
    }
}
