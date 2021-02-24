package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.util.common.text.Text;
import com.github.franckyi.gamehooks.util.common.text.TextFormatting;
import com.github.franckyi.ibeeditor.impl.common.packet.NotifyClientPacket;
import com.github.franckyi.ibeeditor.impl.common.packet.OpenBlockEditorResponsePacket;
import com.github.franckyi.ibeeditor.impl.common.packet.OpenEntityEditorResponsePacket;
import com.github.franckyi.ibeeditor.impl.common.packet.TriggerOpenEditorPacket;

public final class ClientPacketHandler {
    public static void openBlockEditor(OpenBlockEditorResponsePacket packet) {
        if (packet.getBlock().getTag() != null) {
            IBEEditorClient.openBlockEditor(packet.getBlock(), packet.getPos(), packet.isNBT());
        } else {
            GameHooks.client().player().sendMessage(Text.literal("No Block data found", TextFormatting.RED));
        }
    }

    public static void openEntityEditor(OpenEntityEditorResponsePacket packet) {
        if (packet.getEntity().getTag() != null) {
            IBEEditorClient.openEntityEditor(packet.getEntity(), packet.getEntityId(), packet.isNBT());
        } else {
            GameHooks.client().player().sendMessage(Text.literal("No Entity found", TextFormatting.RED));
        }
    }

    public static void serverModInstalled(NotifyClientPacket packet) {
        IBEEditorClient.serverModInstalled = true;
        ClientNetwork.notifyServer();
    }

    public static void openEditorTriggered(TriggerOpenEditorPacket packet) {
        switch (packet.getType()) {
            case TriggerOpenEditorPacket.WORLD:
                IBEEditorClient.openWorldEditor(packet.isNBT());
                break;
            case TriggerOpenEditorPacket.ITEM:
                if (!IBEEditorClient.tryOpenItemEditor(packet.isNBT())) {
                    GameHooks.client().player().sendMessage(Text.literal("No Item found", TextFormatting.RED));
                }
                break;
            case TriggerOpenEditorPacket.BLOCK:
                if (!IBEEditorClient.tryOpenBlockEditor(packet.isNBT())) {
                    GameHooks.client().player().sendMessage(Text.literal("No Block data found", TextFormatting.RED));
                }
                break;
            case TriggerOpenEditorPacket.ENTITY:
                if (!IBEEditorClient.tryOpenEntityEditor(packet.isNBT())) {
                    GameHooks.client().player().sendMessage(Text.literal("No Entity found", TextFormatting.RED));
                }
                break;
            case TriggerOpenEditorPacket.SELF:
                IBEEditorClient.requestOpenSelfEditor(packet.isNBT());
                break;
        }
    }
}
