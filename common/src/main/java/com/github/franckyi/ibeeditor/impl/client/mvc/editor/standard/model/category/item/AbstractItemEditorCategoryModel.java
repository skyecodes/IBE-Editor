package com.github.franckyi.ibeeditor.impl.client.mvc.editor.standard.model.category.item;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.standard.model.StandardEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.AbstractEditorCategoryModel;
import com.github.franckyi.minecraft.api.common.tag.CompoundTag;

public abstract class AbstractItemEditorCategoryModel extends AbstractEditorCategoryModel {
    public AbstractItemEditorCategoryModel(String name, StandardEditorModel editor) {
        super(name, editor);
    }

    public abstract void apply(CompoundTag tag);
}
