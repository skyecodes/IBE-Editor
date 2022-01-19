package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.ibeeditor.client.context.BlockEditorContext;
import com.github.franckyi.ibeeditor.client.screen.model.category.block.BlockStateCategoryModel;
import com.github.franckyi.ibeeditor.client.screen.model.category.block.ContainerCategoryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;

public class BlockEditorModel extends StandardEditorModel {
    public BlockEditorModel(BlockEditorContext context) {
        super(context);
    }

    @Override
    public BlockEditorContext getContext() {
        return (BlockEditorContext) super.getContext();
    }

    @Override
    protected void setupCategories() {
        if (!getContext().getBlockState().getProperties().isEmpty()) {
            getCategories().add(new BlockStateCategoryModel(this));
        }
        if (getContext().getBlockEntity() instanceof BaseContainerBlockEntity) {
            getCategories().add(new ContainerCategoryModel(this));
        }
    }

    @Override
    public MutableComponent getEditorName() {
        return ModTexts.BLOCK;
    }
}
