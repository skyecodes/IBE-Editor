package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.ValueEditorEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.LabeledEditorEntryView;

public abstract class ValueEditorEntryController<M extends ValueEditorEntryModel<?>, V extends LabeledEditorEntryView> extends LabeledEditorEntryController<M, V> {
    public ValueEditorEntryController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getResetButton().onAction(this::resetModel);
    }

    protected void resetModel() {
        model.reset();
    }
}
