package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EntryModel;

public abstract class ItemCategoryModel extends CategoryModel {
    private final ICompoundTag baseData;
    private ICompoundTag newData;

    public ItemCategoryModel(IText name, ItemEditorModel editor) {
        super(name, editor);
        baseData = editor.getTarget().getData();
    }

    public void apply(ICompoundTag nbt) {
        this.newData = nbt;
        getEntries().forEach(EntryModel::apply);
    }

    @Override
    public ItemEditorModel getEditor() {
        return (ItemEditorModel) super.getEditor();
    }

    protected ICompoundTag getBaseData() {
        return baseData;
    }

    protected ICompoundTag getBaseTag() {
        return getBaseData().getCompound("tag");
    }

    protected ICompoundTag getNewData() {
        return newData;
    }

    protected ICompoundTag getNewTag() {
        return getNewData().getOrCreateCompound("tag");
    }
}
