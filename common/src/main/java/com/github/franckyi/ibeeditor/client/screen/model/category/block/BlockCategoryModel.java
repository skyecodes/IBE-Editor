package com.github.franckyi.ibeeditor.client.screen.model.category.block;

import com.github.franckyi.ibeeditor.client.screen.model.BlockEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.EntryModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public abstract class BlockCategoryModel extends CategoryModel {
    private final BlockState baseState;
    private final CompoundTag baseTag;
    private BlockState newState;
    private CompoundTag newTag;

    public BlockCategoryModel(Component name, BlockEditorModel editor) {
        super(name, editor);
        baseState = editor.getTarget().state();
        baseTag = editor.getTarget().tag();
    }

    public BlockState apply(BlockState state, CompoundTag tag) {
        this.newState = state;
        this.newTag = tag;
        getEntries().forEach(EntryModel::apply);
        return newState;
    }

    protected <T extends Comparable<T>> void updateState(Property<T> property, T value) {
        newState = newState.setValue(property, value);
    }

    public BlockState getBaseState() {
        return baseState;
    }

    public CompoundTag getBaseTag() {
        return baseTag;
    }

    public BlockState getNewState() {
        return newState;
    }

    public CompoundTag getNewTag() {
        return newTag;
    }
}
