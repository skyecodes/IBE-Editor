package com.github.franckyi.ibeeditor.common.network;

import com.github.franckyi.ibeeditor.PlatformUtil;
import com.github.franckyi.ibeeditor.client.ClientContext;
import com.github.franckyi.ibeeditor.client.logic.ClientEditorCommandLogic;
import com.github.franckyi.ibeeditor.client.logic.ClientEditorResponseLogic;
import com.github.franckyi.ibeeditor.common.ServerContext;
import com.github.franckyi.ibeeditor.common.logic.ServerEditorRequestLogic;
import com.github.franckyi.ibeeditor.common.logic.ServerEditorUpdateLogic;
import net.minecraft.server.level.ServerPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class NetworkManager {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final NetworkHandler.Client<ModNotificationPacket.Server> SERVER_NOTIFICATION = new NetworkHandler.Client<>(ModNotificationPacket.Server.class, "ibeeditor:network/server_notification", ModNotificationPacket.Server.SERIALIZER, ClientContext::onServerNotification);
    public static final NetworkHandler.Server<ModNotificationPacket.Client> CLIENT_NOTIFICATION = new NetworkHandler.Server<>(ModNotificationPacket.Client.class, "ibeeditor:network/client_notification", ModNotificationPacket.Client.SERIALIZER, ServerContext::addModdedClient);
    public static final NetworkHandler.Client<EditorCommandPacket> EDITOR_COMMAND = new NetworkHandler.Client<>(EditorCommandPacket.class, "ibeeditor:network/editor_command", EditorCommandPacket.SERIALIZER, ClientEditorCommandLogic::onEditorCommand);
    public static final NetworkHandler.Server<MainHandItemEditorPacket.Request> MAIN_HAND_ITEM_EDITOR_REQUEST = new NetworkHandler.Server<>(MainHandItemEditorPacket.Request.class, "ibeeditor:network/main_hand_item_editor_request", MainHandItemEditorPacket.Request.SERIALIZER, ServerEditorRequestLogic::onMainHandItemEditorRequest);
    public static final NetworkHandler.Client<MainHandItemEditorPacket.Response> MAIN_HAND_ITEM_EDITOR_RESPONSE = new NetworkHandler.Client<>(MainHandItemEditorPacket.Response.class, "ibeeditor:network/main_hand_item_editor_response", MainHandItemEditorPacket.Response.SERIALIZER, ClientEditorResponseLogic::onMainHandItemEditorResponse);
    public static final NetworkHandler.Server<MainHandItemEditorPacket.Update> MAIN_HAND_ITEM_EDITOR_UPDATE = new NetworkHandler.Server<>(MainHandItemEditorPacket.Update.class, "ibeeditor:network/main_hand_item_editor_update", MainHandItemEditorPacket.Update.SERIALIZER, ServerEditorUpdateLogic::onMainHandItemEditorUpdate);
    public static final NetworkHandler.Server<PlayerInventoryItemEditorPacket.Request> PLAYER_INVENTORY_ITEM_EDITOR_REQUEST = new NetworkHandler.Server<>(PlayerInventoryItemEditorPacket.Request.class, "ibeeditor:network/player_inventory_item_editor_request", PlayerInventoryItemEditorPacket.Request.SERIALIZER, ServerEditorRequestLogic::onPlayerInventoryItemEditorRequest);
    public static final NetworkHandler.Client<PlayerInventoryItemEditorPacket.Response> PLAYER_INVENTORY_ITEM_EDITOR_RESPONSE = new NetworkHandler.Client<>(PlayerInventoryItemEditorPacket.Response.class, "ibeeditor:network/player_inventory_item_editor_response", PlayerInventoryItemEditorPacket.Response.SERIALIZER, ClientEditorResponseLogic::onPlayerInventoryItemEditorResponse);
    public static final NetworkHandler.Server<PlayerInventoryItemEditorPacket.Update> PLAYER_INVENTORY_ITEM_EDITOR_UPDATE = new NetworkHandler.Server<>(PlayerInventoryItemEditorPacket.Update.class, "ibeeditor:network/player_inventory_item_editor_update", PlayerInventoryItemEditorPacket.Update.SERIALIZER, ServerEditorUpdateLogic::onPlayerInventoryItemEditorUpdate);
    public static final NetworkHandler.Server<BlockInventoryItemEditorPacket.Request> BLOCK_INVENTORY_ITEM_EDITOR_REQUEST = new NetworkHandler.Server<>(BlockInventoryItemEditorPacket.Request.class, "ibeeditor:network/block_inventory_item_editor_request", BlockInventoryItemEditorPacket.Request.SERIALIZER, ServerEditorRequestLogic::onBlockInventoryItemEditorRequest);
    public static final NetworkHandler.Client<BlockInventoryItemEditorPacket.Response> BLOCK_INVENTORY_ITEM_EDITOR_RESPONSE = new NetworkHandler.Client<>(BlockInventoryItemEditorPacket.Response.class, "ibeeditor:network/block_inventory_item_editor_response", BlockInventoryItemEditorPacket.Response.SERIALIZER, ClientEditorResponseLogic::onBlockInventoryItemEditorResponse);
    public static final NetworkHandler.Server<BlockInventoryItemEditorPacket.Update> BLOCK_INVENTORY_ITEM_EDITOR_UPDATE = new NetworkHandler.Server<>(BlockInventoryItemEditorPacket.Update.class, "ibeeditor:network/block_inventory_item_editor_update", BlockInventoryItemEditorPacket.Update.SERIALIZER, ServerEditorUpdateLogic::onBlockInventoryItemEditorUpdate);
    public static final NetworkHandler.Server<BlockEditorPacket.Request> BLOCK_EDITOR_REQUEST = new NetworkHandler.Server<>(BlockEditorPacket.Request.class, "ibeeditor:network/block_editor_request", BlockEditorPacket.Request.SERIALIZER, ServerEditorRequestLogic::onBlockEditorRequest);
    public static final NetworkHandler.Client<BlockEditorPacket.Response> BLOCK_EDITOR_RESPONSE = new NetworkHandler.Client<>(BlockEditorPacket.Response.class, "ibeeditor:network/block_editor_response", BlockEditorPacket.Response.SERIALIZER, ClientEditorResponseLogic::onBlockEditorResponse);
    public static final NetworkHandler.Server<BlockEditorPacket.Update> BLOCK_EDITOR_UPDATE = new NetworkHandler.Server<>(BlockEditorPacket.Update.class, "ibeeditor:network/block_editor_update", BlockEditorPacket.Update.SERIALIZER, ServerEditorUpdateLogic::onBlockEditorUpdate);
    public static final NetworkHandler.Server<EntityEditorPacket.Request> ENTITY_EDITOR_REQUEST = new NetworkHandler.Server<>(EntityEditorPacket.Request.class, "ibeeditor:network/entity_editor_request", EntityEditorPacket.Request.SERIALIZER, ServerEditorRequestLogic::onEntityEditorRequest);
    public static final NetworkHandler.Client<EntityEditorPacket.Response> ENTITY_EDITOR_RESPONSE = new NetworkHandler.Client<>(EntityEditorPacket.Response.class, "ibeeditor:network/entity_editor_response", EntityEditorPacket.Response.SERIALIZER, ClientEditorResponseLogic::onEntityEditorResponse);
    public static final NetworkHandler.Server<EntityEditorPacket.Update> ENTITY_EDITOR_UPDATE = new NetworkHandler.Server<>(EntityEditorPacket.Update.class, "ibeeditor:network/entity_editor_update", EntityEditorPacket.Update.SERIALIZER, ServerEditorUpdateLogic::onEntityEditorUpdate);

    public static void setup() {
        PlatformUtil.registerClientHandler(SERVER_NOTIFICATION);
        PlatformUtil.registerServerHandler(CLIENT_NOTIFICATION);
        PlatformUtil.registerClientHandler(EDITOR_COMMAND);
        PlatformUtil.registerServerHandler(MAIN_HAND_ITEM_EDITOR_REQUEST);
        PlatformUtil.registerClientHandler(MAIN_HAND_ITEM_EDITOR_RESPONSE);
        PlatformUtil.registerServerHandler(MAIN_HAND_ITEM_EDITOR_UPDATE);
        PlatformUtil.registerServerHandler(PLAYER_INVENTORY_ITEM_EDITOR_REQUEST);
        PlatformUtil.registerClientHandler(PLAYER_INVENTORY_ITEM_EDITOR_RESPONSE);
        PlatformUtil.registerServerHandler(PLAYER_INVENTORY_ITEM_EDITOR_UPDATE);
        PlatformUtil.registerServerHandler(BLOCK_INVENTORY_ITEM_EDITOR_REQUEST);
        PlatformUtil.registerClientHandler(BLOCK_INVENTORY_ITEM_EDITOR_RESPONSE);
        PlatformUtil.registerServerHandler(BLOCK_INVENTORY_ITEM_EDITOR_UPDATE);
        PlatformUtil.registerServerHandler(BLOCK_EDITOR_REQUEST);
        PlatformUtil.registerClientHandler(BLOCK_EDITOR_RESPONSE);
        PlatformUtil.registerServerHandler(BLOCK_EDITOR_UPDATE);
        PlatformUtil.registerServerHandler(ENTITY_EDITOR_REQUEST);
        PlatformUtil.registerClientHandler(ENTITY_EDITOR_RESPONSE);
        PlatformUtil.registerServerHandler(ENTITY_EDITOR_UPDATE);
    }

    public static <P> void sendToServer(NetworkHandler.Server<P> handler, P packet) {
        LOGGER.debug("Sending {} packet to server", handler.getLocation());
        PlatformUtil.sendToServer(handler, packet);
    }

    public static <P> void sendToClient(ServerPlayer player, NetworkHandler.Client<P> handler, P packet) {
        LOGGER.debug("Sending {} packet to player {}", handler.getLocation(), player.getGameProfile().getName());
        PlatformUtil.sendToClient(player, handler, packet);
    }
}
