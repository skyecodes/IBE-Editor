package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.LabeledEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.LabeledEntryView;

public abstract class LabeledEntryController<M extends LabeledEntryModel, V extends LabeledEntryView> extends EntryController<M, V> {
    public LabeledEntryController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getLabel().labelProperty().bind(model.labelProperty());
        view.getRoot().setWeight(view.getLabel(), model.getLabelWeight());
    }
}
