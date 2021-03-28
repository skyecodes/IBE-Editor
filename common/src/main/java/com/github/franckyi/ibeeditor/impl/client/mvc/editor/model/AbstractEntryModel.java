package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EntryModel;

public abstract class AbstractEntryModel implements EntryModel {
    protected final BooleanProperty validProperty = Bindings.getPropertyFactory().ofBoolean(true);

    @Override
    public BooleanProperty validProperty() {
        return validProperty;
    }
}
