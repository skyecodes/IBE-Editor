package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.category.config;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.category.AbstractCategoryModel;

public abstract class AbstractConfigCategoryModel extends AbstractCategoryModel {
    public AbstractConfigCategoryModel(String name, EditorModel editor) {
        super(name, editor);
    }

    public abstract void apply();
}
