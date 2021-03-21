package com.github.franckyi.ibeeditor.impl.server;

import com.github.franckyi.ibeeditor.impl.common.Networking;
import com.github.franckyi.ibeeditor.impl.common.packet.*;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.network.Packet;
import com.github.franckyi.minecraft.api.common.world.Block;
import com.github.franckyi.minecraft.api.common.world.Entity;
import com.github.franckyi.minecraft.api.common.world.Player;
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

    public static void sendWorldEditorCommand(Player sender, boolean nbt) {
        sendEditorCommand(sender, EditorCommandPacket.WORLD, nbt);
    }

    public static void sendItemEditorCommand(Player sender, boolean nbt) {
        sendEditorCommand(sender, EditorCommandPacket.ITEM, nbt);
    }

    public static void sendBlockEditorCommand(Player sender, boolean nbt) {
        sendEditorCommand(sender, EditorCommandPacket.BLOCK, nbt);
    }

    public static void sendEntityEditorCommand(Player sender, boolean nbt) {
        sendEditorCommand(sender, EditorCommandPacket.ENTITY, nbt);
    }

    public static void sendSelfEditorCommand(Player sender, boolean nbt) {
        sendEditorCommand(sender, EditorCommandPacket.SELF, nbt);
    }

    private static void sendEditorCommand(Player sender, byte type, boolean nbt) {
        send(Networking.EDITOR_COMMAND, sender, new EditorCommandPacket(type, nbt));
    }

    private static void send(String id, Player player, Packet packet) {
        LOGGER.debug("Sending packet {} to player {}", id, player);
        Minecraft.getCommon().getNetwork().sendToClient(id, player, packet);
    }
}
