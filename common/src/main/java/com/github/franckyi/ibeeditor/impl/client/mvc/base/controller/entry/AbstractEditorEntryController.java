package com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.entry;

import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.api.client.mvc.base.controller.EditorEntryController;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorEntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.view.EditorEntryView;

public abstract class AbstractEditorEntryController<M extends EditorEntryModel, V extends EditorEntryView> extends AbstractController<M, V> implements EditorEntryController<M, V> {
    public AbstractEditorEntryController(M model, V view) {
        super(model, view);
    }
}
