package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.category.item;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.category.AbstractCategoryModel;
import com.github.franckyi.minecraft.api.common.tag.CompoundTag;

public abstract class AbstractItemCategoryModel extends AbstractCategoryModel {
    public AbstractItemCategoryModel(String name, EditorModel editor) {
        super(name, editor);
    }

    public abstract void apply(CompoundTag tag);
}
