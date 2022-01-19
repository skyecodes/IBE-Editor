package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.ibeeditor.client.context.EditorContext;
import com.github.franckyi.ibeeditor.client.screen.model.category.EditorCategoryModel;
import net.minecraft.network.chat.MutableComponent;

public abstract class StandardEditorModel extends CategoryEntryScreenModel<EditorCategoryModel> implements EditorModel {
    private final EditorContext<?> context;

    public StandardEditorModel(EditorContext<?> context) {
        this.context = context;
    }

    @Override
    public void apply() {
        getCategories().forEach(EditorCategoryModel::apply);
    }

    @Override
    public void update() {
        EditorModel.super.update();
    }

    @Override
    public EditorContext<?> getContext() {
        return context;
    }

    public abstract MutableComponent getEditorName();
}
