package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.ibeeditor.base.client.mvc.model.EditorCategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.EntityEditorModel;

public abstract class EntityEditorCategoryModel extends EditorCategoryModel {
    public EntityEditorCategoryModel(String name, EntityEditorModel editor) {
        super(name, editor);
    }
}
