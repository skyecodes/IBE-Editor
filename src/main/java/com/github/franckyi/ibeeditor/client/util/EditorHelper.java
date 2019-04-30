package com.github.franckyi.ibeeditor.client.util;

import com.github.franckyi.ibeeditor.client.editor.entity.EntityEditor;
import com.github.franckyi.ibeeditor.client.editor.item.ItemEditor;
import com.github.franckyi.ibeeditor.network.IBENetworkHandler;
import com.github.franckyi.ibeeditor.network.block.InitBlockEditorRequest;
import com.github.franckyi.ibeeditor.network.item.BlockInventoryItemEditorMessage;
import com.github.franckyi.ibeeditor.network.item.EntityInventoryItemEditorMessage;
import com.github.franckyi.ibeeditor.network.item.MainHandItemEditorMessage;
import com.github.franckyi.ibeeditor.network.item.PlayerInventoryItemEditorMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public final class EditorHelper {

    private static final Minecraft mc = Minecraft.getInstance();

    public static boolean openEditor() {
        return openEntityEditor() || openBlockEditor() || openItemEditor() || openSelfEditor();
    }

    public static boolean openItemEditor() {
        ItemStack heldItem = mc.player.getHeldItemMainhand();
        if (!heldItem.isEmpty()) {
            openItemEditor(heldItem);
            return true;
        }
        return false;
    }

    public static boolean openEntityEditor() {
        RayTraceResult res = mc.objectMouseOver;
        if (res.type == RayTraceResult.Type.ENTITY && res.entity != null) {
            openEntityEditor(res.entity);
            return true;
        }
        return false;
    }

    public static boolean openBlockEditor() {
        RayTraceResult res = mc.objectMouseOver;
        if (res.type == RayTraceResult.Type.BLOCK) {
            openBlockEditor(res.getBlockPos());
            return true;
        }
        return false;
    }

    public static boolean openSelfEditor() {
        openEntityEditor(mc.player);
        return true;
    }

    public static void openEntityEditor(Entity entity) {
        if (checkPermissions()) {
            new EntityEditor(entity);
        }
    }

    public static void openBlockEditor(BlockPos blockPos) {
        if (checkPermissions()) {
            IBENetworkHandler.getModChannel().sendToServer(new InitBlockEditorRequest(blockPos));
        }
    }

    public static void openItemEditor(ItemStack itemStack) {
        if (checkPermissions()) {
            new ItemEditor(itemStack, MainHandItemEditorMessage::new);
        }
    }

    public static void openItemEditorFromGui(Slot slot) {
        if (checkPermissions()) {
            new ItemEditor(slot.getStack(), itemStack ->
                    new PlayerInventoryItemEditorMessage(itemStack, slot.getSlotIndex()));
        }
    }

    public static void openItemEditorFromGui(Slot slot, BlockPos blockPos) {
        if (checkPermissions()) {
            new ItemEditor(slot.getStack(), itemStack ->
                    new BlockInventoryItemEditorMessage(itemStack, slot.getSlotIndex(), blockPos));
        }
    }

    public static void openItemEditorFromGui(Slot slot, Entity entity) {
        if (checkPermissions()) {
            new ItemEditor(slot.getStack(), itemStack ->
                    new EntityInventoryItemEditorMessage(itemStack, slot.getSlotIndex(), entity.getEntityId()));
        }
    }

    public static boolean checkPermissions() {
        if (mc.player.isCreative()) {
            return true;
        }
        mc.player.sendMessage(new TextComponentString(
                TextFormatting.RED + "You must be in creative mode in order to use the editor."));
        return false;
    }

}
