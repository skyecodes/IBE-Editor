package com.github.franckyi.ibeeditor.base.server;

import com.github.franckyi.gameadapter.api.common.BlockData;
import com.github.franckyi.gameadapter.api.common.IPlayer;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.ibeeditor.base.common.EditorType;
import com.github.franckyi.ibeeditor.base.common.ModNetwork;
import com.github.franckyi.ibeeditor.base.common.Packet;
import com.github.franckyi.ibeeditor.base.common.packet.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ServerNetworkEmitter {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void sendServerNotification(IPlayer sender) {
        send(ModNetwork.SERVER_NOTIFICATION, sender, new ServerNotificationPacket());
    }

    public static void sendBlockEditorResponse(IPlayer sender, BlockEditorRequestPacket packet, BlockData block) {
        send(ModNetwork.BLOCK_EDITOR_RESPONSE, sender, new BlockEditorResponsePacket(packet, block));
    }

    public static void sendEntityEditorResponse(IPlayer sender, EntityEditorRequestPacket packet, ICompoundTag entity) {
        send(ModNetwork.ENTITY_EDITOR_RESPONSE, sender, new EntityEditorResponsePacket(packet, entity));
    }

    public static void sendWorldEditorCommand(IPlayer sender, EditorType type) {
        sendEditorCommand(sender, EditorCommandPacket.TARGET_WORLD, type);
    }

    public static void sendItemEditorCommand(IPlayer sender, EditorType type) {
        sendEditorCommand(sender, EditorCommandPacket.TARGET_ITEM, type);
    }

    public static void sendBlockEditorCommand(IPlayer sender, EditorType type) {
        sendEditorCommand(sender, EditorCommandPacket.TARGET_BLOCK, type);
    }

    public static void sendEntityEditorCommand(IPlayer sender, EditorType type) {
        sendEditorCommand(sender, EditorCommandPacket.TARGET_ENTITY, type);
    }

    public static void sendSelfEditorCommand(IPlayer sender, EditorType type) {
        sendEditorCommand(sender, EditorCommandPacket.TARGET_SELF, type);
    }

    private static void sendEditorCommand(IPlayer sender, byte target, EditorType type) {
        send(ModNetwork.EDITOR_COMMAND, sender, new EditorCommandPacket(target, type));
    }

    private static void send(String id, IPlayer player, Packet packet) {
        LOGGER.debug("Sending packet {} to player {}", id, player.getProfileName());
        ModNetwork.get().sendToClient(id, player, packet);
    }
}
