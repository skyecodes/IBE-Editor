package com.github.franckyi.ibeeditor.base.client.mvc.editor.standard.model.category.item;

import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.base.model.AbstractEditorCategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.editor.standard.model.ItemEditorModel;

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
