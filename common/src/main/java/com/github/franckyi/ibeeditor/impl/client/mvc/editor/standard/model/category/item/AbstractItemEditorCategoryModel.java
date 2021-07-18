package com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.category.item;

import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorEntryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.AbstractEditorCategoryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.ItemEditorModel;
import com.github.franckyi.minecraft.api.common.tag.CompoundTag;

public abstract class AbstractItemEditorCategoryModel extends AbstractEditorCategoryModel {
    private final CompoundTag baseData;
    private CompoundTag newData;

    public AbstractItemEditorCategoryModel(String name, ItemEditorModel editor) {
        super(name, editor);
        baseData = editor.getTarget().getData();
    }

    public void apply(CompoundTag nbt) {
        this.newData = nbt;
        getEntries().forEach(EditorEntryModel::apply);
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
