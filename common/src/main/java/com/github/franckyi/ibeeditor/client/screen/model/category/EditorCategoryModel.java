package com.github.franckyi.ibeeditor.client.screen.model.category;

import com.github.franckyi.ibeeditor.client.screen.model.StandardEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.EntryModel;
import com.github.franckyi.ibeeditor.common.EditorContext;
import net.minecraft.network.chat.Component;

public abstract class EditorCategoryModel extends CategoryModel {
    protected EditorCategoryModel(Component name, StandardEditorModel parent) {
        super(name, parent);
    }

    @Override
    public StandardEditorModel getParent() {
        return (StandardEditorModel) super.getParent();
    }

    public EditorContext getContext() {
        return getParent().getContext();
    }

    public void apply() {
        getEntries().forEach(EntryModel::apply);
    }
}
