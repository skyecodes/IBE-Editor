package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.ibeeditor.client.ClientUtil;
import com.github.franckyi.ibeeditor.client.Vault;
import com.github.franckyi.ibeeditor.client.screen.model.category.vault.VaultCategoryModel;
import com.github.franckyi.ibeeditor.client.screen.model.category.vault.VaultEntityCategoryModel;
import com.github.franckyi.ibeeditor.client.screen.model.category.vault.VaultItemCategoryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;

public class VaultScreenModel extends CategoryEntryScreenModel<VaultCategoryModel> {
    @Override
    protected void setupCategories() {
        getCategories().addAll(
                new VaultItemCategoryModel(this),
                new VaultEntityCategoryModel(this)
        );
    }

    @Override
    public void apply() {
        Vault.getInstance().clear();
        getCategories().forEach(VaultCategoryModel::apply);
        Vault.save();
        ClientUtil.showMessage(ModTexts.Messages.successUpdate(ModTexts.VAULT));
    }
}
