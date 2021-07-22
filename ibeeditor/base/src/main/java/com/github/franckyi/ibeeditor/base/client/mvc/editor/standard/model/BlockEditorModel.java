package com.github.franckyi.ibeeditor.base.client.mvc.editor.standard.model;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.gameadapter.api.common.world.Block;
import com.github.franckyi.ibeeditor.base.client.mvc.editor.standard.model.category.block.AbstractBlockEditorCategoryModel;

import java.util.function.Consumer;

public class BlockEditorModel extends AbstractStandardEditorModel<Block, AbstractBlockEditorCategoryModel> {
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
