package com.github.franckyi.ibeeditor.client.screen.model.category.entity;

import com.github.franckyi.ibeeditor.client.screen.model.EntityEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import net.minecraft.network.chat.Component;

public abstract class EntityCategoryModel extends CategoryModel {
    public EntityCategoryModel(Component name, EntityEditorModel editor) {
        super(name, editor);
    }
}
