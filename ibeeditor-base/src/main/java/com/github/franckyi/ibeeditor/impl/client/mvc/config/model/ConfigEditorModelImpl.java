package com.github.franckyi.ibeeditor.impl.client.mvc.config.model;

import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.ibeeditor.api.client.mvc.config.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.AbstractListEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.config.model.category.AbstractConfigEditorCategoryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.config.model.category.ClientConfigEditorCategoryModel;

public class ConfigEditorModelImpl extends AbstractListEditorModel<AbstractConfigEditorCategoryModel> implements ConfigEditorModel {

    @Override
    protected void setupCategories() {
        getCategories().add(new ClientConfigEditorCategoryModel(this));
    }

    @Override
    public void apply() {
        getCategories().forEach(AbstractConfigEditorCategoryModel::apply);
        Guapi.getScreenHandler().hideScene();
    }
}
