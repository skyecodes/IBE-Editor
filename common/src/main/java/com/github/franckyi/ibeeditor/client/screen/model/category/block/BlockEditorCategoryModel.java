package com.github.franckyi.ibeeditor.client.screen.model.category.block;

import com.github.franckyi.ibeeditor.client.screen.model.BlockEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.category.EditorCategoryModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public abstract class BlockEditorCategoryModel extends EditorCategoryModel {
    public BlockEditorCategoryModel(Component name, BlockEditorModel editor) {
        super(name, editor);
    }

    protected <T extends Comparable<T>> void updateState(Property<T> property, T value) {
        getContext().setBlockState(getContext().getBlockState().setValue(property, value));
    }

    protected BlockState getBlockState() {
        return getContext().getBlockState();
    }

    protected CompoundTag getData() {
        return getContext().getTag();
    }
}
