package com.github.franckyi.ibeeditor.client.screen.model.category.vault;

import com.github.franckyi.ibeeditor.client.Vault;
import com.github.franckyi.ibeeditor.client.screen.model.VaultScreenModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.EntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.vault.VaultItemEntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;

public class VaultItemCategoryModel extends VaultCategoryModel {
    public VaultItemCategoryModel(VaultScreenModel parent) {
        super(ModTexts.ITEM, parent);
    }

    @Override
    protected void setupEntries() {
        Vault.getInstance().getItems().stream().map(ItemStack::of)
                .forEach(itemStack -> getEntries().add(new VaultItemEntryModel(this, itemStack)));
    }

    @Override
    public int getEntryListStart() {
        return 0;
    }

    @Override
    public EntryModel createNewListEntry() {
        return new VaultItemEntryModel(this, ItemStack.EMPTY);
    }

    @Override
    protected MutableComponent getAddListEntryButtonTooltip() {
        return ModTexts.ITEM;
    }
}
