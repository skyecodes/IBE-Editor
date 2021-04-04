package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.category.block;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.category.AbstractCategoryModel;

public abstract class AbstractBlockCategoryModel extends AbstractCategoryModel {
    public AbstractBlockCategoryModel(String name, EditorModel editor) {
        super(name, editor);
    }
}
