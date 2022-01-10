package com.github.franckyi.ibeeditor.client.screen.controller.entry;

import com.github.franckyi.ibeeditor.client.screen.model.entry.LabeledEntryModel;
import com.github.franckyi.ibeeditor.client.screen.view.entry.LabeledEntryView;

public abstract class LabeledEntryController<M extends LabeledEntryModel, V extends LabeledEntryView> extends EntryController<M, V> {
    public LabeledEntryController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getLabel().labelProperty().bind(model.labelProperty());
        if (view.getLabel().getParent() == view.getRoot()) {
            view.getRoot().setWeight(view.getLabel(), model.getLabelWeight());
        }
    }
}
