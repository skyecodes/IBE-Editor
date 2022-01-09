package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.ibeeditor.client.screen.model.category.block.BlockCategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.category.BlockStateCategoryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiConsumer;

public class BlockEditorModel extends StandardEditorModel<BlockEditorModel.Target, BlockCategoryModel> {
    public BlockEditorModel(BlockState state, CompoundTag tag, BiConsumer<BlockState, CompoundTag> action, Component disabledTooltip) {
        super(new Target(state, tag), target -> action.accept(target.state(), target.tag()), disabledTooltip, ModTexts.BLOCK);
    }

    @Override
    protected void setupCategories() {
        getCategories().addAll(new BlockStateCategoryModel(this));
    }

    @Override
    public Target applyChanges() {
        var state = getTarget().state();
        var tag = getTarget().tag();
        for (BlockCategoryModel category : getCategories()) {
            state = category.apply(state, tag);
        }
        return new Target(state, tag);
    }

    public record Target(BlockState state, CompoundTag tag) {
    }
}
