package com.github.franckyi.ibeeditor.client.logic;

import com.github.franckyi.ibeeditor.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.client.context.BlockEditorContext;
import com.github.franckyi.ibeeditor.client.context.EntityEditorContext;
import com.github.franckyi.ibeeditor.client.context.ItemEditorContext;
import com.github.franckyi.ibeeditor.common.ModTexts;
import com.github.franckyi.ibeeditor.common.network.*;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public final class ClientEditorResponseLogic {
    public static void onMainHandItemEditorResponse(MainHandItemEditorPacket.Response response) {
        ModScreenHandler.openEditor(response.getEditorType(), new ItemEditorContext(response.getItemStack(),
                getErrorTooltip(response.hasPermission(), ModTexts.ITEM), true,
                context -> ClientEditorUpdateLogic.updateMainHandItem(response, context)));
    }

    public static void onPlayerInventoryItemEditorResponse(PlayerInventoryItemEditorPacket.Response response) {
        ModScreenHandler.openEditor(response.getEditorType(), new ItemEditorContext(response.getItemStack(),
                getErrorTooltip(response.hasPermission(), ModTexts.ITEM), true,
                context -> ClientEditorUpdateLogic.updatePlayerInventoryItem(response, context)));
    }

    public static void onBlockInventoryItemEditorResponse(BlockInventoryItemEditorPacket.Response response) {
        ModScreenHandler.openEditor(response.getEditorType(), new ItemEditorContext(response.getItemStack(),
                getErrorTooltip(response.hasPermission(), ModTexts.ITEM), true,
                context -> ClientEditorUpdateLogic.updateBlockInventoryItem(response, context)));
    }

    public static void onBlockEditorResponse(BlockEditorPacket.Response response) {
        ModScreenHandler.openEditor(response.getEditorType(), new BlockEditorContext(response.getBlockState(), response.getTag(),
                getErrorTooltip(response.hasPermission(), ModTexts.BLOCK),
                context -> ClientEditorUpdateLogic.updateBlock(response, context)));
    }

    public static void onEntityEditorResponse(EntityEditorPacket.Response response) {
        ModScreenHandler.openEditor(response.getEditorType(), new EntityEditorContext(response.getTag(),
                getErrorTooltip(response.hasPermission(), ModTexts.ENTITY), true,
                context -> ClientEditorUpdateLogic.updateEntity(response, context)));
    }

    private static Component getErrorTooltip(boolean hasPermission, MutableComponent arg) {
        return hasPermission ? null : ModTexts.errorPermissionDenied(arg);
    }
}
