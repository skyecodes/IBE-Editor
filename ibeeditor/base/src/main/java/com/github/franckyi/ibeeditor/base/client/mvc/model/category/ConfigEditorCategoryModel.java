package com.github.franckyi.ibeeditor.base.client.mvc.model.category;

import com.github.franckyi.ibeeditor.base.client.mvc.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.EditorCategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EditorEntryModel;

public abstract class ConfigEditorCategoryModel extends EditorCategoryModel {
    public ConfigEditorCategoryModel(String name, ConfigEditorModel editor) {
        super(name, editor);
    }

    public void apply() {
        getEntries().forEach(EditorEntryModel::apply);
    }
}
