package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.world.Block;
import com.github.franckyi.ibeeditor.base.client.mvc.model.category.BlockCategoryModel;

import java.util.function.Consumer;

public class BlockEditorModel extends StandardEditorModel<Block, BlockCategoryModel> {
    public BlockEditorModel(Block block, Consumer<Block> action, Text disabledTooltip) {
        super(block, action, disabledTooltip, "ibeeditor.text.block");
    }

    @Override
    protected void setupCategories() {
    }

    @Override
    public Block applyChanges() {
        return getTarget(); // TODO
    }
}
