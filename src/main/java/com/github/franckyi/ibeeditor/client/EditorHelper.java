package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.ibeeditor.client.gui.editor.entity.EntityEditor;
import com.github.franckyi.ibeeditor.client.gui.editor.item.ItemEditor;
import com.github.franckyi.ibeeditor.common.network.IBENetworkHandler;
import com.github.franckyi.ibeeditor.common.network.editor.block.InitBlockEditorRequest;
import com.github.franckyi.ibeeditor.common.network.editor.item.BlockInventoryItemEditorMessage;
import com.github.franckyi.ibeeditor.common.network.editor.item.EntityInventoryItemEditorMessage;
import com.github.franckyi.ibeeditor.common.network.editor.item.MainHandItemEditorMessage;
import com.github.franckyi.ibeeditor.common.network.editor.item.PlayerInventoryItemEditorMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public final class EditorHelper {

    private static final ITextComponent MUST_INSTALL = new StringTextComponent(TextFormatting.RED +
            "IBE Editor must be installed on the server in order to use it.");
    private static final ITextComponent MUST_CREATIVE = new StringTextComponent(TextFormatting.RED +
            "You must be in creative mode in order to use the editor.");

    private static final Minecraft mc = Minecraft.getInstance();
    private static boolean serverEnabled;

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
        if (res instanceof EntityRayTraceResult) {
            openEntityEditor(((EntityRayTraceResult) res).getEntity());
            return true;
        }
        return false;
    }

    public static boolean openBlockEditor() {
        RayTraceResult res = mc.objectMouseOver;
        if (res instanceof BlockRayTraceResult) {
            openBlockEditor(((BlockRayTraceResult) res).getPos());
            return true;
        }
        return false;
    }

    public static boolean openSelfEditor() {
        openEntityEditor(mc.player);
        return true;
    }

    static void openItemEditorFromGui(Slot slot) {
        if (checkPermissions()) {
            new ItemEditor(slot.getStack(), itemStack ->
                    new PlayerInventoryItemEditorMessage(itemStack, slot.getSlotIndex()));
        }
    }

    static void openItemEditorFromGui(Slot slot, BlockPos blockPos) {
        if (checkPermissions()) {
            new ItemEditor(slot.getStack(), itemStack ->
                    new BlockInventoryItemEditorMessage(itemStack, slot.getSlotIndex(), blockPos));
        }
    }

    static void openItemEditorFromGui(Slot slot, Entity entity) {
        if (checkPermissions()) {
            new ItemEditor(slot.getStack(), itemStack ->
                    new EntityInventoryItemEditorMessage(itemStack, slot.getSlotIndex(), entity.getEntityId()));
        }
    }

    private static void openEntityEditor(Entity entity) {
        if (checkPermissions()) {
            new EntityEditor(entity);
        }
    }

    private static void openBlockEditor(BlockPos blockPos) {
        if (checkPermissions()) {
            IBENetworkHandler.getModChannel().sendToServer(new InitBlockEditorRequest(blockPos));
        }
    }

    private static void openItemEditor(ItemStack itemStack) {
        if (checkPermissions()) {
            new ItemEditor(itemStack, MainHandItemEditorMessage::new);
        }
    }

    private static boolean checkPermissions() {
        if (mc.player.isCreative()) {
            if (serverEnabled) {
                return true;
            } else {
                mc.player.sendMessage(MUST_INSTALL);
            }
        } else {
            mc.player.sendMessage(MUST_CREATIVE);
        }
        return false;
    }

    public static void enableServer() {
        serverEnabled = true;
    }

    public static void disableServer() {
        serverEnabled = false;
    }
}
