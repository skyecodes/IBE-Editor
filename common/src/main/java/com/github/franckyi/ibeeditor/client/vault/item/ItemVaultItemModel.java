package com.github.franckyi.ibeeditor.client.vault.item;

import com.github.franckyi.ibeeditor.client.vault.VaultItemModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

public class ItemVaultItemModel extends VaultItemModel {
    private final ItemStack item;
    public ItemVaultItemModel(FriendlyByteBuf buf) {
        item = ItemStack.of(buf.readNbt());
    }

    public ItemStack getItem() {
        return item;
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeNbt(item.save(new CompoundTag()));
    }
}
