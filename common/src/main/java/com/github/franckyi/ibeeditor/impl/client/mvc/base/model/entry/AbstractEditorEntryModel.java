package com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorEntryModel;

public abstract class AbstractEditorEntryModel implements EditorEntryModel {
    private final EditorCategoryModel category;
    private final BooleanProperty validProperty = DataBindings.getPropertyFactory().createBooleanProperty(true);

    protected AbstractEditorEntryModel(EditorCategoryModel category) {
        this.category = category;
        validProperty().addListener(() -> getCategory().updateValidity());
    }

    @Override
    public BooleanProperty validProperty() {
        return validProperty;
    }

    @Override
    public EditorCategoryModel getCategory() {
        return category;
    }
}
