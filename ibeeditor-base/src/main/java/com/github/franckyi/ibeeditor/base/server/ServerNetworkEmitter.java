package com.github.franckyi.ibeeditor.base.server;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.network.Packet;
import com.github.franckyi.gameadapter.api.common.world.Block;
import com.github.franckyi.gameadapter.api.common.world.Entity;
import com.github.franckyi.gameadapter.api.common.world.Player;
import com.github.franckyi.ibeeditor.base.common.EditorType;
import com.github.franckyi.ibeeditor.base.common.Networking;
import com.github.franckyi.ibeeditor.base.common.packet.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ServerNetworkEmitter {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void sendServerNotification(Player sender) {
        send(Networking.SERVER_NOTIFICATION, sender, new ServerNotificationPacket());
    }

    public static void sendBlockEditorResponse(Player sender, BlockEditorRequestPacket packet, Block block) {
        send(Networking.BLOCK_EDITOR_RESPONSE, sender, new BlockEditorResponsePacket(packet, block));
    }

    public static void sendEntityEditorResponse(Player sender, EntityEditorRequestPacket packet, Entity entity) {
        send(Networking.ENTITY_EDITOR_RESPONSE, sender, new EntityEditorResponsePacket(packet, entity));
    }

    public static void sendWorldEditorCommand(Player sender, EditorType type) {
        sendEditorCommand(sender, EditorCommandPacket.TARGET_WORLD, type);
    }

    public static void sendItemEditorCommand(Player sender, EditorType type) {
        sendEditorCommand(sender, EditorCommandPacket.TARGET_ITEM, type);
    }

    public static void sendBlockEditorCommand(Player sender, EditorType type) {
        sendEditorCommand(sender, EditorCommandPacket.TARGET_BLOCK, type);
    }

    public static void sendEntityEditorCommand(Player sender, EditorType type) {
        sendEditorCommand(sender, EditorCommandPacket.TARGET_ENTITY, type);
    }

    public static void sendSelfEditorCommand(Player sender, EditorType type) {
        sendEditorCommand(sender, EditorCommandPacket.TARGET_SELF, type);
    }

    private static void sendEditorCommand(Player sender, byte target, EditorType type) {
        send(Networking.EDITOR_COMMAND, sender, new EditorCommandPacket(target, type));
    }

    private static void send(String id, Player player, Packet packet) {
        LOGGER.debug("Sending packet {} to player {}", id, player);
        Game.getCommon().getNetwork().sendToClient(id, player, packet);
    }
}
