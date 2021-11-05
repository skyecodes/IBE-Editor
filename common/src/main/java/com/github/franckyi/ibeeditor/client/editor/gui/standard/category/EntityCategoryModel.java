package com.github.franckyi.ibeeditor.client.editor.gui.standard.category;

import com.github.franckyi.ibeeditor.client.editor.gui.standard.CategoryModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.EntityEditorModel;
import net.minecraft.network.chat.Component;

public abstract class EntityCategoryModel extends CategoryModel {
    public EntityCategoryModel(Component name, EntityEditorModel editor) {
        super(name, editor);
    }
}
