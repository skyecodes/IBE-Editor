package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.ValueEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.LabeledEntryView;

public abstract class ValueEntryController<M extends ValueEntryModel<?>, V extends LabeledEntryView> extends LabeledEntryController<M, V> {
    public ValueEntryController(M model, V view) {
        super(model, view);
    }
}
