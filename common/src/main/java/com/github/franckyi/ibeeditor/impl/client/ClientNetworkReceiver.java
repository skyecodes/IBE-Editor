package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.ibeeditor.impl.common.packet.NotifyClientPacket;
import com.github.franckyi.ibeeditor.impl.common.packet.OpenBlockEditorResponsePacket;
import com.github.franckyi.ibeeditor.impl.common.packet.OpenEntityEditorResponsePacket;
import com.github.franckyi.ibeeditor.impl.common.packet.TriggerOpenEditorPacket;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.util.common.TextFormatting;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public final class ClientNetworkReceiver {
    public static void openBlockEditor(OpenBlockEditorResponsePacket packet) {
        if (packet.getBlock().getData().get() != null || (!packet.isNBT() && packet.getBlock().getState().get() != null)) {
            IBEEditorClient.openBlockEditor(packet.getBlock(), packet.getPos(), packet.isNBT());
        } else {
            Minecraft.getClient().getPlayer().sendMessage(text("No Block data found", TextFormatting.RED));
        }
    }

    public static void openEntityEditor(OpenEntityEditorResponsePacket packet) {
        if (packet.getEntity().getTag() != null) {
            IBEEditorClient.openEntityEditor(packet.getEntity(), packet.getEntityId(), packet.isNBT());
        } else {
            Minecraft.getClient().getPlayer().sendMessage(text("No Entity found", TextFormatting.RED));
        }
    }

    public static void serverModInstalled(NotifyClientPacket packet) {
        IBEEditorClient.setServerModInstalled(true);
        ClientNetworkEmitter.notifyServer();
    }

    public static void openEditorTriggered(TriggerOpenEditorPacket packet) {
        switch (packet.getType()) {
            case TriggerOpenEditorPacket.WORLD:
                IBEEditorClient.openWorldEditor(packet.isNBT());
                break;
            case TriggerOpenEditorPacket.ITEM:
                if (!IBEEditorClient.tryOpenItemEditor(packet.isNBT())) {
                    Minecraft.getClient().getPlayer().sendMessage(text("No Item found", TextFormatting.RED));
                }
                break;
            case TriggerOpenEditorPacket.BLOCK:
                if (!IBEEditorClient.tryOpenBlockEditor(packet.isNBT())) {
                    Minecraft.getClient().getPlayer().sendMessage(text("No Block data found", TextFormatting.RED));
                }
                break;
            case TriggerOpenEditorPacket.ENTITY:
                if (!IBEEditorClient.tryOpenEntityEditor(packet.isNBT())) {
                    Minecraft.getClient().getPlayer().sendMessage(text("No Entity found", TextFormatting.RED));
                }
                break;
            case TriggerOpenEditorPacket.SELF:
                IBEEditorClient.requestOpenSelfEditor(packet.isNBT());
                break;
        }
    }
}
