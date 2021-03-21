package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.ibeeditor.impl.common.Networking;
import com.github.franckyi.ibeeditor.impl.common.packet.ServerNotificationPacket;
import com.github.franckyi.ibeeditor.impl.common.packet.BlockEditorResponsePacket;
import com.github.franckyi.ibeeditor.impl.common.packet.EntityEditorResponsePacket;
import com.github.franckyi.ibeeditor.impl.common.packet.EditorCommandPacket;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.util.common.TextFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public final class ClientNetworkReceiver {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void onBlockEditorResponse(BlockEditorResponsePacket packet) {
        log(Networking.BLOCK_EDITOR_RESPONSE);
        if (packet.getBlock().getData().get() != null || (!packet.isNBT() && packet.getBlock().getState().get() != null)) {
            ClientEditorLogic.openBlockEditor(packet.getBlock(), packet.getPos(), packet.isNBT());
        } else {
            Minecraft.getClient().getPlayer().sendMessage(text("No Block data found", TextFormatting.RED));
        }
    }

    public static void onEntityEditorResponse(EntityEditorResponsePacket packet) {
        log(Networking.ENTITY_EDITOR_RESPONSE);
        if (packet.getEntity().getTag() != null) {
            ClientEditorLogic.openEntityEditor(packet.getEntity(), packet.getEntityId(), packet.isNBT());
        } else {
            Minecraft.getClient().getPlayer().sendMessage(text("No Entity found", TextFormatting.RED));
        }
    }

    public static void onServerNotification(ServerNotificationPacket packet) {
        log(Networking.SERVER_NOTIFICATION);
        ClientContext.setModInstalledOnServer(true);
        ClientNetworkEmitter.sendClientNotification();
    }

    public static void onEditorCommand(EditorCommandPacket packet) {
        log(Networking.EDITOR_COMMAND);
        switch (packet.getType()) {
            case EditorCommandPacket.WORLD:
                ClientEditorLogic.openWorldEditor(packet.isNBT());
                break;
            case EditorCommandPacket.ITEM:
                if (!ClientEditorLogic.tryOpenItemEditor(packet.isNBT())) {
                    Minecraft.getClient().getPlayer().sendMessage(text("No Item found", TextFormatting.RED));
                }
                break;
            case EditorCommandPacket.BLOCK:
                if (!ClientEditorLogic.tryOpenBlockEditor(packet.isNBT())) {
                    Minecraft.getClient().getPlayer().sendMessage(text("No Block data found", TextFormatting.RED));
                }
                break;
            case EditorCommandPacket.ENTITY:
                if (!ClientEditorLogic.tryOpenEntityEditor(packet.isNBT())) {
                    Minecraft.getClient().getPlayer().sendMessage(text("No Entity found", TextFormatting.RED));
                }
                break;
            case EditorCommandPacket.SELF:
                ClientEditorLogic.tryOpenSelfEditor(packet.isNBT());
                break;
        }
    }

    private static void log(String id) {
        LOGGER.debug("Receiving packet {}", id);
    }
}
