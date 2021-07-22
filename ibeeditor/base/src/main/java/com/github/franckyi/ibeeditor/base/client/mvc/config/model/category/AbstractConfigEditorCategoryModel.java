package com.github.franckyi.ibeeditor.base.client.mvc.config.model.category;

import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorEntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.config.model.ConfigEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.base.model.AbstractEditorCategoryModel;

public abstract class AbstractConfigEditorCategoryModel extends AbstractEditorCategoryModel {
    public AbstractConfigEditorCategoryModel(String name, ConfigEditorModel editor) {
        super(name, editor);
    }

    public void apply() {
        getEntries().forEach(EditorEntryModel::apply);
    }
}
