package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EditorModel;

public abstract class AbstractBlockCategoryModel extends AbstractCategoryModel {
    public AbstractBlockCategoryModel(String name, EditorModel editor) {
        super(name, editor);
    }
}
