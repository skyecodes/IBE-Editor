package com.github.franckyi.ibeeditor.impl.client.mvc.config.model;

import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.config.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.model.AbstractListEditorModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.config.model.category.AbstractConfigEditorCategoryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.config.model.category.ClientConfigEditorCategoryModel;
import com.github.franckyi.minecraft.Minecraft;

public class ConfigEditorModelImpl extends AbstractListEditorModel implements ConfigEditorModel {
    private final ClientConfigEditorCategoryModel client;

    public ConfigEditorModelImpl() {
        getCategories().addAll(
                client = new ClientConfigEditorCategoryModel(this)
        );
    }

    @Override
    @SuppressWarnings("unchecked")
    public ObservableList<AbstractConfigEditorCategoryModel> getCategories() {
        return (ObservableList<AbstractConfigEditorCategoryModel>) super.getCategories();
    }

    @Override
    public void apply() {
        getCategories().forEach(AbstractConfigEditorCategoryModel::apply);
        Minecraft.getClient().getScreenHandler().hideScene();
    }

    public void syncEntries() {
        client.syncEntries();
    }

    @Override
    public void updateValidity() {
        setValid(getCategories().stream().allMatch(EditorCategoryModel::isValid));
    }
}
