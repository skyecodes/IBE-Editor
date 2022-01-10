package com.github.franckyi.ibeeditor.client.screen.model.category.config;

import com.github.franckyi.ibeeditor.client.screen.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.EntryModel;
import net.minecraft.network.chat.Component;

public abstract class ConfigCategoryModel extends CategoryModel {
    public ConfigCategoryModel(Component name, ConfigEditorModel editor) {
        super(name, editor);
    }

    public void apply() {
        getEntries().forEach(EntryModel::apply);
    }
}
