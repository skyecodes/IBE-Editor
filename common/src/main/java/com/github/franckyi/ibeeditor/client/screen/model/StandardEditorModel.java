package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.ibeeditor.client.screen.model.category.EditorCategoryModel;
import com.github.franckyi.ibeeditor.common.EditorContext;

public abstract class StandardEditorModel extends CategoryEntryScreenModel<EditorCategoryModel> implements EditorModel {
    private final EditorContext context;

    public StandardEditorModel(EditorContext context) {
        this.context = context;
    }

    @Override
    public EditorContext getContext() {
        return context;
    }

    @Override
    public void apply() {
        getCategories().forEach(EditorCategoryModel::apply);
    }
}
