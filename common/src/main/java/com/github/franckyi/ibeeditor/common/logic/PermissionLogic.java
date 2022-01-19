package com.github.franckyi.ibeeditor.common.logic;

import com.github.franckyi.ibeeditor.common.CommonConfiguration;
import net.minecraft.server.level.ServerPlayer;

public class PermissionLogic {
    public static boolean hasPermission(ServerPlayer player) {
        return player.hasPermissions(CommonConfiguration.INSTANCE.getPermissionLevel()) && (!CommonConfiguration.INSTANCE.isCreativeOnly() || player.isCreative());
    }
}
