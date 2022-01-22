package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.ibeeditor.client.context.EntityEditorContext;
import com.github.franckyi.ibeeditor.client.screen.model.category.entity.EntityGeneralCategoryModel;

public class EntityEditorModel extends StandardEditorModel {
    public EntityEditorModel(EntityEditorContext context) {
        super(context);
    }

    @Override
    protected void setupCategories() {
        getCategories().addAll(
                new EntityGeneralCategoryModel(this)
        );
    }

}
