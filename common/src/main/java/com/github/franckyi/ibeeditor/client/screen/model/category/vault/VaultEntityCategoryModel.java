package com.github.franckyi.ibeeditor.client.screen.model.category.vault;

import com.github.franckyi.ibeeditor.client.Vault;
import com.github.franckyi.ibeeditor.client.screen.model.VaultScreenModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.vault.VaultEntityEntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;

public class VaultEntityCategoryModel extends VaultCategoryModel {
    public VaultEntityCategoryModel(VaultScreenModel parent) {
        super(ModTexts.ENTITY, parent);
    }

    @Override
    protected void setupEntries() {
        Vault.getInstance().getEntities().forEach(entity -> getEntries().add(new VaultEntityEntryModel(this, entity)));
    }

    @Override
    public int getEntryListStart() {
        return 0;
    }

    @Override
    protected boolean canAddEntryInList() {
        return false;
    }
}
