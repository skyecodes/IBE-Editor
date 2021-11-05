package com.github.franckyi.ibeeditor.client.vault;

import com.github.franckyi.ibeeditor.client.vault.item.ItemVaultItemModel;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Function;

public enum VaultItemType {
    ITEM(ItemVaultItemModel::new), BLOCK(null), ENTITY(null), TAG(null), COLOR(null);

    private final Function<FriendlyByteBuf, VaultItemModel> reader;

    VaultItemType(Function<FriendlyByteBuf, VaultItemModel> reader) {
        this.reader = reader;
    }

    public VaultItemModel read(FriendlyByteBuf buf) {
        return reader.apply(buf);
    }
}
