package com.github.franckyi.ibeeditor.client.screen.controller.entry;

import com.github.franckyi.ibeeditor.client.screen.model.entry.ValueEntryModel;
import com.github.franckyi.ibeeditor.client.screen.view.entry.LabeledEntryView;

public abstract class ValueEntryController<M extends ValueEntryModel<?>, V extends LabeledEntryView> extends LabeledEntryController<M, V> {
    public ValueEntryController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getResetButton().disableProperty().bind(model.valueChangedProperty().not());
    }
}
