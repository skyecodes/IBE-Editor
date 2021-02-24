package com.github.franckyi.ibeeditor.impl.server;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.api.common.Player;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.ibeeditor.impl.common.IBEEditorNetwork;
import com.github.franckyi.ibeeditor.impl.common.packet.*;

public final class ServerNetwork {
    private static Network<?> network() {
        return GameHooks.common().network();
    }

    public static void notifyClient(Player sender) {
        network().sendToClient(IBEEditorNetwork.NOTIFY_CLIENT, sender.getPlayerEntity(), new NotifyClientPacket());
    }

    public static void openBlockEditorResponse(Player sender, OpenBlockEditorRequestPacket packet, Block block) {
        network().sendToClient(IBEEditorNetwork.OPEN_BLOCK_EDITOR_RESPONSE, sender.getPlayerEntity(), new OpenBlockEditorResponsePacket(packet, block));
    }

    public static void openEntityEditorResponse(Player sender, OpenEntityEditorRequestPacket packet, Entity entity) {
        network().sendToClient(IBEEditorNetwork.OPEN_ENTITY_EDITOR_RESPONSE, sender.getPlayerEntity(), new OpenEntityEditorResponsePacket(packet, entity));
    }

    public static void triggerOpenWorldEditor(Player sender, boolean nbt) {
        triggerOpenEditor(sender, TriggerOpenEditorPacket.WORLD, nbt);
    }

    public static void triggerOpenItemEditor(Player sender, boolean nbt) {
        triggerOpenEditor(sender, TriggerOpenEditorPacket.ITEM, nbt);
    }

    public static void triggerOpenBlockEditor(Player sender, boolean nbt) {
        triggerOpenEditor(sender, TriggerOpenEditorPacket.BLOCK, nbt);
    }

    public static void triggerOpenEntityEditor(Player sender, boolean nbt) {
        triggerOpenEditor(sender, TriggerOpenEditorPacket.ENTITY, nbt);
    }

    public static void triggerOpenSelfEditor(Player sender, boolean nbt) {
        triggerOpenEditor(sender, TriggerOpenEditorPacket.SELF, nbt);
    }

    private static void triggerOpenEditor(Player sender, byte type, boolean nbt) {
        network().sendToClient(IBEEditorNetwork.TRIGGER_OPEN_EDITOR, sender.getPlayerEntity(), new TriggerOpenEditorPacket(type, nbt));
    }
}
