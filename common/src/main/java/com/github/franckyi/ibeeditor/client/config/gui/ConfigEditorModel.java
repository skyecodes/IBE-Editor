package com.github.franckyi.ibeeditor.client.config.gui;

import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.ibeeditor.client.editor.gui.ListEditorModel;

public class ConfigEditorModel extends ListEditorModel<ConfigCategoryModel> {

    @Override
    protected void setupCategories() {
        getCategories().add(new ClientConfigCategoryModel(this));
    }

    @Override
    public void apply() {
        getCategories().forEach(ConfigCategoryModel::apply);
        Guapi.getScreenHandler().hideScene();
    }
}
