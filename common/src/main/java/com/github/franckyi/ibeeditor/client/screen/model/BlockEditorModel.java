package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.ibeeditor.client.screen.model.category.block.BlockStateCategoryModel;
import com.github.franckyi.ibeeditor.client.screen.model.category.block.ContainerCategoryModel;
import com.github.franckyi.ibeeditor.common.EditorContext;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;

public class BlockEditorModel extends StandardEditorModel {
    public BlockEditorModel(EditorContext context) {
        super(context);
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
    public boolean saveToVault() {
        return false;
    }
}
