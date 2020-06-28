package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.ibeeditor.client.gui.editor.block.BlockEditor;
import com.github.franckyi.ibeeditor.client.gui.editor.entity.EntityEditor;
import com.github.franckyi.ibeeditor.client.gui.editor.item.ItemEditor;
import com.github.franckyi.ibeeditor.common.IBEConfiguration;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.items.CapabilityItemHandler;

public final class EditorHelper {
    private static final ITextComponent MUST_CREATIVE = new StringTextComponent(TextFormatting.RED +
            "You must be in creative mode in order to use the editor.");

    private static final Minecraft mc = Minecraft.getInstance();
    private static boolean serverEnabled;
    private static BlockPos warningPos;

    public static boolean openEditor() {
        return openEntityEditor() || openBlockEditor() || openItemEditor() || openSelfEditor();
    }

    public static boolean openItemEditor() {
        ItemStack heldItem = mc.player.getHeldItemMainhand();
        if (!heldItem.isEmpty()) {
            openItemEditorMainHand(heldItem);
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
            BlockRayTraceResult block = (BlockRayTraceResult) res;
            if (!mc.world.isAirBlock(block.getPos())) {
                openBlockEditor(block.getPos());
                return true;
            }
        }
        return false;
    }

    public static boolean openSelfEditor() {
        openEntityEditor(mc.player);
        return true;
    }

    private static void openItemEditorMainHand(ItemStack itemStack) {
        if (checkPermissions()) {
            if (isServerEnabled()) {
                ItemEditor.withConsumer(itemStack, itemStack1 -> IBENetworkHandler.getModChannel().sendToServer(new MainHandItemEditorMessage(itemStack1)));
            } else {
                if (mc.playerController.isInCreativeMode()) {
                    ItemEditor.withConsumer(itemStack, itemStack1 ->
                            mc.playerController.sendSlotPacket(itemStack1, mc.player.inventory.currentItem));
                } else {
                    ItemEditor.withConsumer(itemStack, itemStack1 -> ClientUtils.handleCommand(
                            ClientUtils.getReplaceItemCommandForPlayerMainHand(itemStack1)));
                }
            }
        }
    }

    static void openItemEditorFromPlayerInventory(Slot slot) {
        if (checkPermissions()) {
            if (isServerEnabled()) {
                ItemEditor.withConsumer(slot.getStack(), itemStack -> IBENetworkHandler.getModChannel().sendToServer(
                        new PlayerInventoryItemEditorMessage(itemStack, slot.getSlotIndex())));
            } else {
                if (mc.playerController.isInCreativeMode()) {
                    ItemEditor.withConsumer(slot.getStack(), itemStack1 ->
                            mc.playerController.sendSlotPacket(itemStack1, slot.slotNumber));
                } else {
                    ItemEditor.withConsumer(slot.getStack(), itemStack1 -> ClientUtils.handleCommand(
                            ClientUtils.getReplaceItemCommandForPlayer(itemStack1, slot)));
                }
            }
        }
    }

    static void openItemEditorFromBlockInventory(Slot slot, BlockPos blockPos) {
        if (checkPermissions()) {
            if (isServerEnabled()) {
                ItemEditor.withConsumer(slot.getStack(), itemStack -> IBENetworkHandler.getModChannel().sendToServer(
                        new BlockInventoryItemEditorMessage(itemStack, slot.getSlotIndex(), blockPos)));
            } else {
                ItemEditor.withConsumer(slot.getStack(), itemStack -> ClientUtils.handleCommand(
                        ClientUtils.getReplaceItemCommandForBlock(itemStack, slot, blockPos)));
            }
        }
    }

    static void openItemEditorFromEntityInventory(Slot slot, Entity entity) {
        if (checkPermissions()) {
            if (isServerEnabled()) {
                ItemEditor.withConsumer(slot.getStack(), itemStack -> IBENetworkHandler.getModChannel().sendToServer(
                        new EntityInventoryItemEditorMessage(itemStack, slot.getSlotIndex(), entity.getEntityId())));
            } else {
                ItemEditor.withConsumer(slot.getStack(), itemStack -> ClientUtils.handleCommand(
                        ClientUtils.getReplaceItemCommandForEntity(itemStack, slot, entity)));
            }
        }
    }

    private static void openEntityEditor(Entity entity) {
        if (checkPermissions()) {
            new EntityEditor(entity);
        }
    }

    private static void openBlockEditor(BlockPos blockPos) {
        if (checkPermissions()) {
            if (isServerEnabled()) {
                IBENetworkHandler.getModChannel().sendToServer(new InitBlockEditorRequest(blockPos));
            } else {
                TileEntity te = mc.world.getTileEntity(blockPos);
                if (te != null && te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).isPresent()) {
                    if (!blockPos.equals(warningPos)) {
                        mc.player.sendMessage(new StringTextComponent("[IBE Editor] Be careful, editing this block will delete all the items it contains! Open the editor again if you want to continue.")
                                .applyTextStyle(TextFormatting.RED));
                        warningPos = blockPos;
                        return;
                    } else {
                        warningPos = null;
                    }
                }
                new BlockEditor(blockPos, te);
            }
        }
    }

    private static boolean checkPermissions() {
        if (!IBEConfiguration.CLIENT.creativeModeOnly.get() || mc.player.isCreative()) {
            return true;
        } else {
            mc.player.sendMessage(MUST_CREATIVE);
        }
        return false;
    }

    public static boolean isServerEnabled() {
        return serverEnabled;
    }

    public static void setServerEnabled(boolean serverEnabled) {
        EditorHelper.serverEnabled = serverEnabled;
    }
}
