package com.github.franckyi.ibeeditor.client.screen.model.category.item;

import com.github.franckyi.ibeeditor.client.screen.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.category.EditorCategoryModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;

public abstract class ItemEditorCategoryModel extends EditorCategoryModel {
    protected ItemEditorCategoryModel(Component name, ItemEditorModel parent) {
        super(name, parent);
    }

    protected CompoundTag getData() {
        return getContext().getTag();
    }

    protected CompoundTag getTag() {
        return getData().getCompound("tag");
    }

    protected CompoundTag getSubTag(String name) {
        return getTag().getCompound(name);
    }

    protected CompoundTag getOrCreateTag() {
        if (!getData().contains("tag", Tag.TAG_COMPOUND)) {
            var tag = new CompoundTag();
            getData().put("tag", tag);
            return tag;
        }
        return getTag();
    }

    protected CompoundTag getOrCreateSubTag(String name) {
        if (!getOrCreateTag().contains(name, Tag.TAG_COMPOUND)) {
            var tag = new CompoundTag();
            getTag().put(name, tag);
            return tag;
        }
        return getSubTag(name);
    }
}
