package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EntryModel;

public abstract class ConfigCategoryModel extends CategoryModel {
    public ConfigCategoryModel(IText name, ConfigEditorModel editor) {
        super(name, editor);
    }

    public void apply() {
        getEntries().forEach(EntryModel::apply);
    }
}
