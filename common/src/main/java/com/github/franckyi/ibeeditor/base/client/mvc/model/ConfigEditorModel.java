package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.ibeeditor.base.client.mvc.model.category.ClientConfigCategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.category.ConfigCategoryModel;

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
