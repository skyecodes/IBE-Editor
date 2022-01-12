package com.github.franckyi.ibeeditor.client.screen.model.entry.vault;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.ibeeditor.client.Vault;
import com.github.franckyi.ibeeditor.client.screen.model.category.vault.VaultItemCategoryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.EntryModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class VaultItemEntryModel extends EntryModel {
    private final ObjectProperty<ItemStack> itemStackProperty;

    public VaultItemEntryModel(VaultItemCategoryModel parent, ItemStack itemStack) {
        super(parent);
        itemStackProperty = ObjectProperty.create(itemStack);
    }

    public ItemStack getItemStack() {
        return itemStackProperty().getValue();
    }

    public ObjectProperty<ItemStack> itemStackProperty() {
        return itemStackProperty;
    }

    public void setItemStack(ItemStack value) {
        itemStackProperty().setValue(value);
    }

    @Override
    public void apply() {
        Vault.getInstance().saveItem(getItemStack().save(new CompoundTag()));
    }

    @Override
    public Type getType() {
        return Type.VAULT_ITEM;
    }
}
