package com.github.franckyi.ibeeditor.client.screen.model.category.vault;

import com.github.franckyi.ibeeditor.client.screen.model.CategoryEntryScreenModel;
import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.EntryModel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public abstract class VaultCategoryModel extends CategoryModel {
    protected VaultCategoryModel(Component name, CategoryEntryScreenModel<?> parent) {
        super(name, parent);
    }

    @Override
    public int getEntryListStart() {
        return 0;
    }

    @Override
    public abstract EntryModel createNewListEntry();

    @Override
    protected abstract MutableComponent getAddListEntryButtonTooltip();

    public abstract void apply();
}
