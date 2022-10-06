package com.github.franckyi.ibeeditor.common.logic;

import com.github.franckyi.ibeeditor.common.CommonUtil;
import com.github.franckyi.ibeeditor.common.network.GiveVaultItemPacket;
import net.minecraft.server.level.ServerPlayer;

public class ServerVaultActionLogic {
    public static void onGiveVaultItem(ServerPlayer player, GiveVaultItemPacket response) {
        player.getInventory().setItem(response.slot(), response.itemStack());
        CommonUtil.showVaultItemGiveSuccess(player);
    }
}
