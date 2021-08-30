package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.client.mvc.model.BlockEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;

public abstract class BlockCategoryModel extends CategoryModel {
    public BlockCategoryModel(IText name, BlockEditorModel editor) {
        super(name, editor);
    }
}
