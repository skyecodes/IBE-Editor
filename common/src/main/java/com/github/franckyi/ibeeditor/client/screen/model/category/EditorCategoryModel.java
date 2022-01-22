package com.github.franckyi.ibeeditor.client.screen.model.category;

import com.github.franckyi.ibeeditor.client.context.EditorContext;
import com.github.franckyi.ibeeditor.client.screen.model.StandardEditorModel;
import net.minecraft.network.chat.Component;

public abstract class EditorCategoryModel extends CategoryModel {
    protected EditorCategoryModel(Component name, StandardEditorModel parent) {
        super(name, parent);
    }

    @Override
    public StandardEditorModel getParent() {
        return (StandardEditorModel) super.getParent();
    }

    public EditorContext<?> getContext() {
        return getParent().getContext();
    }
}
