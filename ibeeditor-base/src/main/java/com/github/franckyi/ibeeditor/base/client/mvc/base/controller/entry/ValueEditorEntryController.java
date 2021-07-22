package com.github.franckyi.ibeeditor.base.client.mvc.base.controller.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.base.model.entry.ValueEditorEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.base.view.entry.LabeledEditorEntryView;

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
