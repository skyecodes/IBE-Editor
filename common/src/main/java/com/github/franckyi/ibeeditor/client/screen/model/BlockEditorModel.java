package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.ibeeditor.client.screen.model.category.block.BlockCategoryModel;
import com.github.franckyi.ibeeditor.client.screen.model.category.block.BlockStateCategoryModel;
import com.github.franckyi.ibeeditor.client.screen.model.category.block.ContainerCategoryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiConsumer;

public class BlockEditorModel extends StandardEditorModel<BlockEditorModel.Target, BlockCategoryModel> {
    private final BlockEntity blockEntity;

    public BlockEditorModel(BlockState state, BlockEntity blockEntity, BiConsumer<BlockState, CompoundTag> action, Component disabledTooltip) {
        super(new Target(state, blockEntity.saveWithId()), target -> action.accept(target.state(), target.tag()), disabledTooltip, ModTexts.BLOCK);
        this.blockEntity = blockEntity;
    }

    @Override
    protected void setupCategories() {
        if (!getTarget().state().getProperties().isEmpty()) {
            getCategories().addAll(new BlockStateCategoryModel(this));
        }
        if (blockEntity instanceof BaseContainerBlockEntity blockEntity) {
            getCategories().addAll(new ContainerCategoryModel(this, blockEntity));
        }
        if (getTarget().tag().contains("Items", Tag.TAG_LIST)) {

        }
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
