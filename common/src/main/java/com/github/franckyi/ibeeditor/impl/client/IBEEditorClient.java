package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.ibeeditor.impl.common.IBEEditorConfiguration;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.MinecraftClient;
import com.github.franckyi.minecraft.api.client.KeyBinding;
import com.github.franckyi.minecraft.api.client.screen.Screen;
import com.github.franckyi.minecraft.api.common.BlockPos;
import com.github.franckyi.minecraft.api.common.Slot;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.api.common.world.*;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.lwjgl.glfw.GLFW;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GUAPIHelper.*;
import static com.github.franckyi.ibeeditor.impl.common.IBEEditorCommon.LOGGER;

public final class IBEEditorClient {
    private static final Marker MARKER = MarkerManager.getMarker("Client");
    private static final Text ERROR_CREATIVE_ITEM = text("You must be in creative mode to update this item.", RED);
    private static final Text ERROR_SERVERMOD_ITEM = text("IBE Editor must be installed on the server to update this item.", RED);
    private static final Text ERROR_SERVERMOD_BLOCK = text("IBE Editor must be installed on the server to update this block.", RED);
    private static final Text ERROR_SERVERMOD_ENTITY = text("IBE Editor must be installed on the server to update this entity.", RED);

    public static KeyBinding editorKey;
    public static KeyBinding nbtEditorKey;
    public static KeyBinding clipboardKey;
    private static boolean modInstalledOnServer;

    public static void init(MinecraftClient client) {
        LOGGER.debug(MARKER, "Initializing IBE Editor - client");
        Minecraft.setClient(client);
        IBEEditorConfiguration.loadClient();
        editorKey = Minecraft.getClient().registerKeyBinding("ibeeditor.key.editor", GLFW.GLFW_KEY_I, "ibeeditor.category");
        nbtEditorKey = Minecraft.getClient().registerKeyBinding("ibeeditor.key.nbt_editor", GLFW.GLFW_KEY_N, "ibeeditor.category");
        clipboardKey = Minecraft.getClient().registerKeyBinding("ibeeditor.key.clipboard", GLFW.GLFW_KEY_J, "ibeeditor.category");
    }

    public static void onKeyInput() {
        if (editorKey.isPressed()) {
            Minecraft.getClient().unlockCursor();
            openWorldEditor(false);
        } else if (nbtEditorKey.isPressed()) {
            Minecraft.getClient().unlockCursor();
            openWorldEditor(true);
        } else if (clipboardKey.isPressed()) {
            Minecraft.getClient().unlockCursor();
            openClipboard();
        }
    }

    public static void openWorldEditor(boolean nbt) {
        if (!(tryOpenEntityEditor(nbt) || tryOpenBlockEditor(nbt) || tryOpenItemEditor(nbt))) {
            requestOpenSelfEditor(nbt);
        }
    }

    public static boolean tryOpenEntityEditor(boolean nbt) {
        WorldEntity entity = Minecraft.getClient().getEntityMouseOver();
        if (entity != null) {
            if (isModInstalledOnServer()) {
                requestOpenEntityEditor(entity.getEntityId(), nbt);
            } else {
                openEntityEditor(entity, entity.getEntityId(), nbt);
            }
            return true;
        }
        return false;
    }

    public static boolean tryOpenBlockEditor(boolean nbt) {
        WorldBlock block = Minecraft.getClient().getBlockMouseOver();
        if (block != null) {
            if (isModInstalledOnServer()) {
                requestOpenBlockEditor(block.getBlockPos(), nbt);
            } else {
                openBlockEditor(block, block.getBlockPos(), nbt);
            }
            return true;
        }
        return false;
    }

    public static boolean tryOpenItemEditor(boolean nbt) {
        Item item = getClientPlayer().getItemMainHand();
        if (item != null) {
            openItemEditor(item, nbt, IBEEditorClient::updatePlayerMainHandItem, isModInstalledOnServer() || getClientPlayer().isCreative() ? null : ERROR_CREATIVE_ITEM);
            return true;
        }
        return false;
    }

    public static void openClipboard() {
        //GameHooks.client().getScreenHandler().showScene(scene(mvc(EditorView.class, new EditorModelImpl()), true, true));
    }

    public static void handleScreenEvent(Screen screen, int keyCode) {
        if (keyCode == editorKey.getKeyCode() || keyCode == nbtEditorKey.getKeyCode()) {
            Slot slot = screen.getInventoryFocusedSlot();
            if (slot.hasStack()) {
                if (slot.isInPlayerInventory()) {
                    openItemEditor(slot.getStack(), keyCode == nbtEditorKey.getKeyCode(), item -> updatePlayerInventoryItem(item, slot.getIndex(), screen.isCreativeInventoryScreen()), isModInstalledOnServer() || getClientPlayer().isCreative() ? null : ERROR_CREATIVE_ITEM);
                } else {
                    WorldBlock block = Minecraft.getClient().getBlockMouseOver();
                    if (block != null) {
                        openItemEditor(slot.getStack(), keyCode == nbtEditorKey.getKeyCode(), item -> updateBlockInventoryItem(item, slot.getIndex(), block.getBlockPos()), isModInstalledOnServer() ? null : ERROR_SERVERMOD_ITEM);
                    }
                }
            }
        }
    }

    public static void openItemEditor(Item item, boolean nbt, Consumer<Item> action, Text disabledTooltip) {
        LOGGER.debug(MARKER, "Opening Item Editor (item={};nbt={})", item.get(), nbt);
        if (nbt) {
            EditorHandler.openNBTEditor(item.getTag(), tag -> action.accept(Minecraft.getCommon().createItem(tag)), disabledTooltip);
        } else {
            EditorHandler.openItemEditor(item, action, disabledTooltip);
        }
    }

    public static void requestOpenBlockEditor(BlockPos blockPos, boolean nbt) {
        LOGGER.debug(MARKER, "Requesting Block Editor (pos={};nbt={})", blockPos.get(), nbt);
        ClientNetworkEmitter.requestOpenBlockEditor(blockPos, nbt);
    }

    public static void openBlockEditor(Block block, BlockPos blockPos, boolean nbt) {
        LOGGER.debug(MARKER, "Opening Block Editor (pos={};nbt={})", blockPos.get(), nbt);
        if (nbt) {
            EditorHandler.openNBTEditor(block.getData(), tag -> updateBlock(blockPos, Minecraft.getCommon().createBlock(block.getState(), tag)), isModInstalledOnServer() ? null : ERROR_SERVERMOD_BLOCK);
        } else {
            EditorHandler.openBlockEditor(block, newBlock -> updateBlock(blockPos, newBlock), isModInstalledOnServer() ? null : ERROR_SERVERMOD_BLOCK);
        }
    }

    public static void requestOpenEntityEditor(int entityId, boolean nbt) {
        LOGGER.debug(MARKER, "Requesting Entity Editor (id={};nbt={})", entityId, nbt);
        ClientNetworkEmitter.requestOpenEntityEditor(entityId, nbt);
    }

    public static void openEntityEditor(Entity entity, int entityId, boolean nbt) {
        LOGGER.debug(MARKER, "Opening Entity Editor (id={};nbt={})", entityId, nbt);
        if (nbt) {
            EditorHandler.openNBTEditor(entity.getTag(), tag -> updateEntity(entityId, Minecraft.getCommon().createEntity(tag)), isModInstalledOnServer() ? null : ERROR_SERVERMOD_ENTITY);
        } else {
            EditorHandler.openEntityEditor(entity, entity1 -> updateEntity(entityId, entity1), isModInstalledOnServer() ? null : ERROR_SERVERMOD_ENTITY);
        }
    }

    public static void requestOpenSelfEditor(boolean nbt) {
        if (isModInstalledOnServer()) {
            requestOpenEntityEditor(getClientPlayer().getEntityId(), nbt);
        } else {
            openEntityEditor(getClientPlayer(), getClientPlayer().getEntityId(), nbt);
        }
    }

    public static boolean isModInstalledOnServer() {
        return modInstalledOnServer;
    }

    public static void setModInstalledOnServer(boolean modInstalledOnServer) {
        LOGGER.debug(MARKER, "Setting 'modInstalledOnServer' to {}", modInstalledOnServer);
        IBEEditorClient.modInstalledOnServer = modInstalledOnServer;
    }

    private static void updatePlayerMainHandItem(Item item) {
        if (isModInstalledOnServer()) {
            ClientNetworkEmitter.updatePlayerMainHandItem(item);
        } else {
            if (getClientPlayer().isCreative()) {
                getClientPlayer().updateMainHandItem(item);
            } else {
                getClientPlayer().sendMessage(ERROR_CREATIVE_ITEM);
            }
        }
        Minecraft.getClient().getScreenHandler().hideScene();
    }

    private static void updatePlayerInventoryItem(Item item, int slotId, boolean isCreativeInventoryScreen) {
        if (isModInstalledOnServer())  {
            ClientNetworkEmitter.updatePlayerInventoryItem(item, slotId);
        } else {
            if (getClientPlayer().isCreative()) {
                if (isCreativeInventoryScreen) {
                    getClientPlayer().updateCreativeInventoryItem(item, slotId);
                } else {
                    getClientPlayer().updateInventoryItem(item, slotId);
                }
            } else {
                getClientPlayer().sendMessage(ERROR_CREATIVE_ITEM);
            }
        }
        Minecraft.getClient().getScreenHandler().hideScene();
    }

    private static void updateBlockInventoryItem(Item item, int slotId, BlockPos blockPos) {
        if (isModInstalledOnServer()) {
            ClientNetworkEmitter.updateBlockInventoryItem(item, slotId, blockPos);
        } else {
            getClientPlayer().sendMessage(ERROR_SERVERMOD_ITEM);
        }
        Minecraft.getClient().getScreenHandler().hideScene();
    }

    private static void updateBlock(BlockPos blockPos, Block block) {
        if (isModInstalledOnServer()) {
            ClientNetworkEmitter.updateBlock(blockPos, block);
        } else {
            getClientPlayer().sendMessage(ERROR_SERVERMOD_BLOCK);
        }
        Minecraft.getClient().getScreenHandler().hideScene();
    }

    private static void updateEntity(int entityId, Entity entity) {
        if (isModInstalledOnServer()) {
            ClientNetworkEmitter.updateEntity(entityId, entity);
        } else {
            getClientPlayer().sendMessage(ERROR_SERVERMOD_ENTITY);
        }
        Minecraft.getClient().getScreenHandler().hideScene();
    }

    private static Player getClientPlayer() {
        return Minecraft.getClient().getPlayer();
    }
}
