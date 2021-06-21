package com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.category.block;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.model.StandardEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.AbstractEditorCategoryModel;

public abstract class AbstractBlockEditorCategoryModel extends AbstractEditorCategoryModel {
    public AbstractBlockEditorCategoryModel(String name, StandardEditorModel editor) {
        super(name, editor);
    }
}
