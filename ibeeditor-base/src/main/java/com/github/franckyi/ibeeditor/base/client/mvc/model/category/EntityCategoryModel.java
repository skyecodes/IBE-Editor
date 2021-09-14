package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.EntityEditorModel;

public abstract class EntityCategoryModel extends CategoryModel {
    public EntityCategoryModel(IText name, EntityEditorModel editor) {
        super(name, editor);
    }
}
