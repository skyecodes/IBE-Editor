package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.ibeeditor.base.client.mvc.model.BlockEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.EditorCategoryModel;

public abstract class BlockEditorCategoryModel extends EditorCategoryModel {
    public BlockEditorCategoryModel(String name, BlockEditorModel editor) {
        super(name, editor);
    }
}
