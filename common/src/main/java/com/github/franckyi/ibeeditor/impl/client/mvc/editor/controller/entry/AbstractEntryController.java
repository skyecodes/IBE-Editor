package com.github.franckyi.ibeeditor.impl.client.mvc.editor.controller.entry;

import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.controller.EntryController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.view.EntryView;

public abstract class AbstractEntryController<M extends EntryModel, V extends EntryView> extends AbstractController<M, V> implements EntryController<M, V> {
    public AbstractEntryController(M model, V view) {
        super(model, view);
    }
}
