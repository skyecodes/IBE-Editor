package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.ibeeditor.client.Vault;
import com.github.franckyi.ibeeditor.client.screen.model.category.vault.VaultCategoryModel;
import com.github.franckyi.ibeeditor.client.screen.model.category.vault.VaultItemCategoryModel;

public class VaultScreenModel extends CategoryEntryScreenModel<VaultCategoryModel> {
    @Override
    protected void setupCategories() {
        getCategories().addAll(
                new VaultItemCategoryModel(this)
        );
    }

    @Override
    public void apply() {
        Vault.getInstance().clear();
        getCategories().forEach(VaultCategoryModel::apply);
        Vault.save();
    }
}
