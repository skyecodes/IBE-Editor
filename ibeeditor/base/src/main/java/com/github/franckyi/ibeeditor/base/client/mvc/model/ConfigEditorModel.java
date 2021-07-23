package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.ibeeditor.base.client.mvc.model.category.ClientConfigEditorCategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.category.ConfigEditorCategoryModel;

public class ConfigEditorModel extends ListEditorModel<ConfigEditorCategoryModel> {

    @Override
    protected void setupCategories() {
        getCategories().add(new ClientConfigEditorCategoryModel(this));
    }

    @Override
    public void apply() {
        getCategories().forEach(ConfigEditorCategoryModel::apply);
        Guapi.getScreenHandler().hideScene();
    }
}
