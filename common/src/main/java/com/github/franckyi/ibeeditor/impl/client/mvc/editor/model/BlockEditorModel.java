package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model;

import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.world.Block;

import java.util.function.Consumer;

public class BlockEditorModel extends AbstractEditorModel<Block, AbstractBlockCategoryModel> {
    public BlockEditorModel(Block block, Consumer<Block> action, Text disabledTooltip) {
        super(block, action, disabledTooltip);
    }

    @Override
    public Block applyChanges() {
        return getTarget(); // TODO
    }
}
