package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.ibeeditor.base.client.mvc.model.BlockEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;

public abstract class BlockCategoryModel extends CategoryModel {
    public BlockCategoryModel(String name, BlockEditorModel editor) {
        super(name, editor);
    }
}
