package com.github.franckyi.ibeeditor.client.logic;

import com.github.franckyi.ibeeditor.common.network.GiveVaultItemPacket;
import com.github.franckyi.ibeeditor.common.network.NetworkManager;
import net.minecraft.world.item.ItemStack;

public final class ClientVaultActionLogic {
    public static void giveVaultItem(int slot, ItemStack itemStack) {
        NetworkManager.sendToServer(NetworkManager.GIVE_VAULT_ITEM, new GiveVaultItemPacket(slot, itemStack));
    }
}
