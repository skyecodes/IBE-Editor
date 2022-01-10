package com.github.franckyi.ibeeditor.client.screen.model.category.item;

import com.github.franckyi.ibeeditor.client.screen.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.EntryModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

public abstract class ItemCategoryModel extends CategoryModel {
    private final CompoundTag baseData;
    private CompoundTag newData;

    public ItemCategoryModel(Component name, ItemEditorModel editor) {
        super(name, editor);
        baseData = editor.getTarget().save(new CompoundTag());
    }

    public void apply(CompoundTag nbt) {
        this.newData = nbt;
        getEntries().forEach(EntryModel::apply);
    }

    @Override
    public ItemEditorModel getEditor() {
        return (ItemEditorModel) super.getEditor();
    }

    protected CompoundTag getBaseData() {
        return baseData;
    }

    protected CompoundTag getBaseTag() {
        return getBaseData().getCompound("tag");
    }

    protected CompoundTag getNewData() {
        return newData;
    }

    protected CompoundTag getNewTag() {
        if (getNewData().contains("tag")) {
            return getNewData().getCompound("tag");
        }
        CompoundTag tag = new CompoundTag();
        getNewData().put("tag", tag);
        return tag;
    }
}
