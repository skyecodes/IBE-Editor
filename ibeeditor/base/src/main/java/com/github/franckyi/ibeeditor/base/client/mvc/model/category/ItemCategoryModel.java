package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EntryModel;

public abstract class ItemCategoryModel extends CategoryModel {
    private final CompoundTag baseData;
    private CompoundTag newData;

    public ItemCategoryModel(String name, ItemEditorModel editor) {
        super(name, editor);
        baseData = editor.getTarget().getData();
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
        return getNewData().getOrCreateCompound("tag");
    }
}
