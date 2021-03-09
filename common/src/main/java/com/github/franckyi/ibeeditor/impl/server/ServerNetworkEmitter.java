package com.github.franckyi.ibeeditor.impl.server;

import com.github.franckyi.ibeeditor.impl.common.IBEEditorNetwork;
import com.github.franckyi.ibeeditor.impl.common.packet.*;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.network.Packet;
import com.github.franckyi.minecraft.api.common.world.Block;
import com.github.franckyi.minecraft.api.common.world.Entity;
import com.github.franckyi.minecraft.api.common.world.Player;

public final class ServerNetworkEmitter {
    public static void notifyClient(Player sender) {
        send(IBEEditorNetwork.NOTIFY_CLIENT, sender, new NotifyClientPacket());
    }

    public static void openBlockEditorResponse(Player sender, OpenBlockEditorRequestPacket packet, Block block) {
        send(IBEEditorNetwork.OPEN_BLOCK_EDITOR_RESPONSE, sender, new OpenBlockEditorResponsePacket(packet, block));
    }

    public static void openEntityEditorResponse(Player sender, OpenEntityEditorRequestPacket packet, Entity entity) {
        send(IBEEditorNetwork.OPEN_ENTITY_EDITOR_RESPONSE, sender, new OpenEntityEditorResponsePacket(packet, entity));
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
        send(IBEEditorNetwork.TRIGGER_OPEN_EDITOR, sender, new TriggerOpenEditorPacket(type, nbt));
    }

    private static void send(String id, Player player, Packet packet) {
        Minecraft.getCommon().getNetwork().sendToClient(id, player, packet);
    }
}
