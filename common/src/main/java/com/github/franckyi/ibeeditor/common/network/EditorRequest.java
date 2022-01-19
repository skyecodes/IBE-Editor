package com.github.franckyi.ibeeditor.common.network;

import com.github.franckyi.ibeeditor.PlatformUtil;
import net.minecraft.server.level.ServerPlayer;

public interface EditorRequest<R> {
    R createResponse(ServerPlayer player);

    NetworkHandler.Client<R> getResponseNetworkHandler();

    default void handleRequestAndSendResponse(ServerPlayer player) {
        PlatformUtil.sendToClient(player, getResponseNetworkHandler(), createResponse(player));
    }
}
