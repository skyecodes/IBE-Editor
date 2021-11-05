package com.github.franckyi.ibeeditor.client.config.gui;

import com.github.franckyi.ibeeditor.client.editor.gui.standard.CategoryModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.EntryModel;
import net.minecraft.network.chat.Component;

public abstract class ConfigCategoryModel extends CategoryModel {
    public ConfigCategoryModel(Component name, ConfigEditorModel editor) {
        super(name, editor);
    }

    public void apply() {
        getEntries().forEach(EntryModel::apply);
    }
}
