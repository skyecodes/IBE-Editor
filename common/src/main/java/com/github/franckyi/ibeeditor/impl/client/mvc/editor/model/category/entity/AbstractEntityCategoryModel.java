package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.category.entity;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.category.AbstractCategoryModel;

public abstract class AbstractEntityCategoryModel extends AbstractCategoryModel {
    public AbstractEntityCategoryModel(String name, EditorModel editor) {
        super(name, editor);
    }
}
