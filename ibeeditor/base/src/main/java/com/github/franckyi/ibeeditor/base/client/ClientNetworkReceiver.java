package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.gameadapter.api.common.IPlayer;
import com.github.franckyi.gameadapter.api.common.world.WorldBlockData;
import com.github.franckyi.ibeeditor.base.common.ModNetwork;
import com.github.franckyi.ibeeditor.base.common.ModTexts;
import com.github.franckyi.ibeeditor.base.common.packet.BlockEditorResponsePacket;
import com.github.franckyi.ibeeditor.base.common.packet.EditorCommandPacket;
import com.github.franckyi.ibeeditor.base.common.packet.EntityEditorResponsePacket;
import com.github.franckyi.ibeeditor.base.common.packet.ServerNotificationPacket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ClientNetworkReceiver {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void onBlockEditorResponse(BlockEditorResponsePacket packet) {
        log(ModNetwork.BLOCK_EDITOR_RESPONSE);
        if (packet.getBlock().getTag() != null || (!packet.getType().isNBT() && packet.getBlock().getState() != null)) {
            ClientEditorLogic.openBlockEditor(new WorldBlockData(packet.getBlock(), packet.getPos()), packet.getType());
        } else {
            player().sendMessage(ModTexts.NO_BLOCK_FOUND_TEXT);
        }
    }

    public static void onEntityEditorResponse(EntityEditorResponsePacket packet) {
        log(ModNetwork.ENTITY_EDITOR_RESPONSE);
        if (packet.getEntity() != null) {
            ClientEditorLogic.openEntityEditor(packet.getEntity(), packet.getEntityId(), packet.getType());
        } else {
            player().sendMessage(ModTexts.NO_ENTITY_FOUND_TEXT);
        }
    }

    public static void onServerNotification(ServerNotificationPacket packet) {
        log(ModNetwork.SERVER_NOTIFICATION);
        ClientContext.setModInstalledOnServer(true);
        ClientNetworkEmitter.sendClientNotification();
    }

    public static void onEditorCommand(EditorCommandPacket packet) {
        log(ModNetwork.EDITOR_COMMAND);
        switch (packet.getTarget()) {
            case EditorCommandPacket.TARGET_WORLD:
                ClientEditorLogic.openWorldEditor(packet.getType());
                break;
            case EditorCommandPacket.TARGET_ITEM:
                if (!ClientEditorLogic.tryOpenItemEditor(packet.getType())) {
                    player().sendMessage(ModTexts.NO_ITEM_FOUND_TEXT);
                }
                break;
            case EditorCommandPacket.TARGET_BLOCK:
                if (!ClientEditorLogic.tryOpenBlockEditor(packet.getType())) {
                    player().sendMessage(ModTexts.NO_BLOCK_FOUND_TEXT);
                }
                break;
            case EditorCommandPacket.TARGET_ENTITY:
                if (!ClientEditorLogic.tryOpenEntityEditor(packet.getType())) {
                    player().sendMessage(ModTexts.NO_ENTITY_FOUND_TEXT);
                }
                break;
            case EditorCommandPacket.TARGET_SELF:
                ClientEditorLogic.tryOpenSelfEditor(packet.getType());
                break;
        }
    }

    private static IPlayer player() {
        return IPlayer.client();
    }

    private static void log(String id) {
        LOGGER.debug("Receiving packet {}", id);
    }
}
