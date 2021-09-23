package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.EntityEditorModel;
import net.minecraft.network.chat.Component;

public abstract class EntityCategoryModel extends CategoryModel {
    public EntityCategoryModel(Component name, EntityEditorModel editor) {
        super(name, editor);
    }
}
