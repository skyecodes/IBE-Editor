package com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model;

import com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.category.block.AbstractBlockEditorCategoryModel;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.world.Block;

import java.util.function.Consumer;

public class BlockEditorModel extends AbstractStandardEditorModel<Block, AbstractBlockEditorCategoryModel> {
    public BlockEditorModel(Block block, Consumer<Block> action, Text disabledTooltip) {
        super(block, action, disabledTooltip, "ibeeditor.text.block");
    }

    @Override
    public Block applyChanges() {
        return getTarget(); // TODO
    }
}
