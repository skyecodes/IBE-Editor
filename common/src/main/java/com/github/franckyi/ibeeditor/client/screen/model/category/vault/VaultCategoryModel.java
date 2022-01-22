package com.github.franckyi.ibeeditor.client.screen.model.category.vault;

import com.github.franckyi.ibeeditor.client.screen.model.CategoryEntryScreenModel;
import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import net.minecraft.network.chat.Component;

public abstract class VaultCategoryModel extends CategoryModel {
    protected VaultCategoryModel(Component name, CategoryEntryScreenModel<?> parent) {
        super(name, parent);
    }
}
