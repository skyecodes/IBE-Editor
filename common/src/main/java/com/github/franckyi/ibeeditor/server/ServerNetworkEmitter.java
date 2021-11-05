package com.github.franckyi.ibeeditor.server;

import com.github.franckyi.ibeeditor.common.EditorType;
import com.github.franckyi.ibeeditor.common.NetworkManager;
import com.github.franckyi.ibeeditor.common.Packet;
import com.github.franckyi.ibeeditor.common.packet.BlockEditorResponsePacket;
import com.github.franckyi.ibeeditor.common.packet.EditorCommandPacket;
import com.github.franckyi.ibeeditor.common.packet.EntityEditorResponsePacket;
import com.github.franckyi.ibeeditor.common.packet.ServerNotificationPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ServerNetworkEmitter {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void sendServerNotification(ServerPlayer sender) {
        send(NetworkManager.SERVER_NOTIFICATION, sender, new ServerNotificationPacket());
    }

    public static void sendBlockEditorResponse(ServerPlayer sender, EditorType editorType, BlockPos pos, BlockState state, CompoundTag tag) {
        send(NetworkManager.BLOCK_EDITOR_RESPONSE, sender, new BlockEditorResponsePacket(editorType, pos, state, tag));
    }

    public static void sendEntityEditorResponse(ServerPlayer sender, EditorType editorType, int entityId, CompoundTag tag) {
        send(NetworkManager.ENTITY_EDITOR_RESPONSE, sender, new EntityEditorResponsePacket(editorType, entityId, tag));
    }

    public static void sendWorldEditorCommand(ServerPlayer sender, EditorType type) {
        sendEditorCommand(sender, EditorCommandPacket.TARGET_WORLD, type);
    }

    public static void sendItemEditorCommand(ServerPlayer sender, EditorType type) {
        sendEditorCommand(sender, EditorCommandPacket.TARGET_ITEM, type);
    }

    public static void sendBlockEditorCommand(ServerPlayer sender, EditorType type) {
        sendEditorCommand(sender, EditorCommandPacket.TARGET_BLOCK, type);
    }

    public static void sendEntityEditorCommand(ServerPlayer sender, EditorType type) {
        sendEditorCommand(sender, EditorCommandPacket.TARGET_ENTITY, type);
    }

    public static void sendSelfEditorCommand(ServerPlayer sender, EditorType type) {
        sendEditorCommand(sender, EditorCommandPacket.TARGET_SELF, type);
    }

    private static void sendEditorCommand(ServerPlayer sender, byte target, EditorType type) {
        send(NetworkManager.EDITOR_COMMAND, sender, new EditorCommandPacket(target, type));
    }

    private static void send(String id, ServerPlayer player, Packet packet) {
        LOGGER.debug("Sending packet {} to player {}", id, player.getGameProfile().getName());
        NetworkManager.sendToClient(id, player, packet);
    }
}
