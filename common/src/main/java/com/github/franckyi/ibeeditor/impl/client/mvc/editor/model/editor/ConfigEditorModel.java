package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.editor;

import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.category.config.AbstractConfigCategoryModel;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.model.category.config.ClientConfigCategoryModel;

public class ConfigEditorModel extends AbstractEditorModel<Void, AbstractConfigCategoryModel> {
    public ConfigEditorModel() {
        super(null, unused -> {}, null, "ibeeditor.gui.settings");
        getCategories().addAll(
                new ClientConfigCategoryModel(this)
        );
    }

    @Override
    protected Void applyChanges() {
        getCategories().forEach(AbstractConfigCategoryModel::apply);
        return null;
    }
}
