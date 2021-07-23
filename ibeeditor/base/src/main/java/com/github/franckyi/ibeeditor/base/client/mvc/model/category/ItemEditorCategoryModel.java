package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.ibeeditor.base.client.mvc.model.EditorCategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EditorEntryModel;

public abstract class ItemEditorCategoryModel extends EditorCategoryModel {
    private final CompoundTag baseData;
    private CompoundTag newData;

    public ItemEditorCategoryModel(String name, ItemEditorModel editor) {
        super(name, editor);
        baseData = editor.getTarget().getData();
    }

    public void apply(CompoundTag nbt) {
        this.newData = nbt;
        getEntries().forEach(EditorEntryModel::apply);
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
