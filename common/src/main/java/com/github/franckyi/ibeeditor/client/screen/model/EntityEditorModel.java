package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.ibeeditor.client.Vault;
import com.github.franckyi.ibeeditor.common.EditorContext;

public class EntityEditorModel extends StandardEditorModel {
    public EntityEditorModel(EditorContext context) {
        super(context);
    }

    @Override
    protected void setupCategories() {
    }

    @Override
    public boolean saveToVault() {
        return Vault.getInstance().saveEntity(getContext().getTag());
    }
}
