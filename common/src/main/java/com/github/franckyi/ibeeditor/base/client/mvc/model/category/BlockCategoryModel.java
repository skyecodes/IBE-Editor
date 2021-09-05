package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.ibeeditor.base.client.mvc.model.BlockEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import net.minecraft.network.chat.Component;

public abstract class BlockCategoryModel extends CategoryModel {
    private final BlockState baseState;
    private final CompoundTag baseTag;
    private BlockState newState;
    private CompoundTag newTag;

    public BlockCategoryModel(Component name, BlockEditorModel editor) {
        super(name, editor);
        baseState = editor.getTarget().getState();
        baseTag = editor.getTarget().getTag();
    }

    public BlockState apply(BlockState state, CompoundTag tag) {
        this.newState = state;
        this.newTag = tag;
        getEntries().forEach(EntryModel::apply);
        return newState;
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

    protected <T extends Comparable<T>> void updateState(Property<T> property, T value) {
        newState = newState.setValue(property, value);
    }
}
