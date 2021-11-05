package com.github.franckyi.ibeeditor.client.editor.gui.standard;

import com.github.franckyi.ibeeditor.client.editor.gui.standard.category.BlockCategoryModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.category.block.BlockInventoryCategoryModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.category.block.BlockStateCategoryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import com.github.franckyi.ibeeditor.common.TagHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Consumer;

public class BlockEditorModel extends StandardEditorModel<Pair<BlockState, CompoundTag>, BlockCategoryModel> {
    public BlockEditorModel(Pair<BlockState, CompoundTag> block, Consumer<Pair<BlockState, CompoundTag>> action, Component disabledTooltip) {
        super(block, action, disabledTooltip, ModTexts.BLOCK);
    }

    @Override
    protected void setupCategories() {
        getCategories().addAll(
                new BlockStateCategoryModel(this)
        );
        if (getTarget().getRight().contains("Items", TagHelper.LIST_ID)) {
            getCategories().add(new BlockInventoryCategoryModel(this));
        }
    }

    @Override
    public Pair<BlockState, CompoundTag> applyChanges() {
        BlockState state = getTarget().getLeft();
        CompoundTag tag = getTarget().getRight() == null ? null : getTarget().getRight().copy();
        for (BlockCategoryModel category : getCategories()) {
            state = category.apply(state, tag);
        }
        return ImmutablePair.of(state, tag);
    }
}
