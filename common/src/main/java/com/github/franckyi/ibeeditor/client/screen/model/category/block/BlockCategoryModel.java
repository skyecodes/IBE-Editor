package com.github.franckyi.ibeeditor.client.screen.model.category.block;

import com.github.franckyi.ibeeditor.client.screen.model.BlockEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import net.minecraft.network.chat.Component;

public abstract class BlockCategoryModel extends CategoryModel {
    public BlockCategoryModel(Component name, BlockEditorModel editor) {
        super(name, editor);
    }
}
