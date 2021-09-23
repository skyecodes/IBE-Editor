package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.ibeeditor.base.client.mvc.model.BlockEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import net.minecraft.network.chat.Component;

public abstract class BlockCategoryModel extends CategoryModel {
    public BlockCategoryModel(Component name, BlockEditorModel editor) {
        super(name, editor);
    }
}
