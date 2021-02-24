package com.github.franckyi.ibeeditor.impl.server;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.api.common.ServerPlayer;
import com.github.franckyi.gamehooks.api.common.network.Network;
import com.github.franckyi.ibeeditor.impl.common.IBEEditorNetwork;
import com.github.franckyi.ibeeditor.impl.common.packet.*;

public final class ServerNetwork {
    private static Network<?> network() {
        return GameHooks.common().network();
    }

    public static void notifyClient(ServerPlayer sender) {
        network().sendToClient(IBEEditorNetwork.NOTIFY_CLIENT, sender.getServerEntity(), new NotifyClientPacket());
    }

    public static void openBlockEditorResponse(ServerPlayer sender, OpenBlockEditorRequestPacket packet, Block block) {
        network().sendToClient(IBEEditorNetwork.OPEN_BLOCK_EDITOR_RESPONSE, sender.getServerEntity(), new OpenBlockEditorResponsePacket(packet, block));
    }

    public static void openEntityEditorResponse(ServerPlayer sender, OpenEntityEditorRequestPacket packet, Entity entity) {
        network().sendToClient(IBEEditorNetwork.OPEN_ENTITY_EDITOR_RESPONSE, sender.getServerEntity(), new OpenEntityEditorResponsePacket(packet, entity));
    }

    public static void triggerOpenWorldEditor(ServerPlayer sender, boolean nbt) {
        triggerOpenEditor(sender, TriggerOpenEditorPacket.WORLD, nbt);
    }

    public static void triggerOpenItemEditor(ServerPlayer sender, boolean nbt) {
        triggerOpenEditor(sender, TriggerOpenEditorPacket.ITEM, nbt);
    }

    public static void triggerOpenBlockEditor(ServerPlayer sender, boolean nbt) {
        triggerOpenEditor(sender, TriggerOpenEditorPacket.BLOCK, nbt);
    }

    public static void triggerOpenEntityEditor(ServerPlayer sender, boolean nbt) {
        triggerOpenEditor(sender, TriggerOpenEditorPacket.ENTITY, nbt);
    }

    public static void triggerOpenSelfEditor(ServerPlayer sender, boolean nbt) {
        triggerOpenEditor(sender, TriggerOpenEditorPacket.SELF, nbt);
    }

    private static void triggerOpenEditor(ServerPlayer sender, byte type, boolean nbt) {
        network().sendToClient(IBEEditorNetwork.TRIGGER_OPEN_EDITOR, sender.getServerEntity(), new TriggerOpenEditorPacket(type, nbt));
    }
}
