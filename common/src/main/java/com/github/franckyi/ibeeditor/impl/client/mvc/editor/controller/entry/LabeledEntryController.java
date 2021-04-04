package com.github.franckyi.ibeeditor.impl.client.mvc.editor.controller.entry;

import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.entry.LabeledEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.entry.LabeledEntryView;

public abstract class LabeledEntryController<M extends LabeledEntryModel, V extends LabeledEntryView> extends AbstractEntryController<M, V> {
    public LabeledEntryController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        view.getLabel().labelProperty().bind(model.labelProperty());
    }
}
