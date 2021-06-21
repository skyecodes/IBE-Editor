package com.github.franckyi.ibeeditor.impl.client.mvc.config.model;

import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.config.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.AbstractListEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.config.model.category.AbstractConfigEditorCategoryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.config.model.category.ClientConfigEditorCategoryModel;

public class ConfigEditorModelImpl extends AbstractListEditorModel implements ConfigEditorModel {
    public ConfigEditorModelImpl() {
        getCategories().addAll(
                new ClientConfigEditorCategoryModel(this)
        );
    }

    @Override
    @SuppressWarnings("unchecked")
    public ObservableList<AbstractConfigEditorCategoryModel> getCategories() {
        return (ObservableList<AbstractConfigEditorCategoryModel>) super.getCategories();
    }

    @Override
    public void apply() {
    }

    @Override
    public void updateValidity() {
        setValid(getCategories().stream().allMatch(EditorCategoryModel::isValid));
    }
}
