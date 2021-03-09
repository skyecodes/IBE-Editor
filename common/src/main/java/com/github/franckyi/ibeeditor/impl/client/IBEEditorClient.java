package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.ibeeditor.impl.common.IBEEditorConfiguration;
import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.MinecraftClient;
import com.github.franckyi.minecraft.api.client.KeyBinding;
import com.github.franckyi.minecraft.api.client.screen.Screen;
import com.github.franckyi.minecraft.api.common.BlockPos;
import com.github.franckyi.minecraft.api.common.Slot;
import com.github.franckyi.minecraft.api.common.world.*;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.lwjgl.glfw.GLFW;

import java.util.function.Consumer;

import static com.github.franckyi.ibeeditor.impl.common.IBEEditorCommon.LOGGER;

public final class IBEEditorClient {
    private static final Marker MARKER = MarkerManager.getMarker("Client");
    public static KeyBinding editorKey;
    public static KeyBinding nbtEditorKey;
    public static KeyBinding clipboardKey;
    private static boolean serverModInstalled;

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
            requestOpenEntityEditor(entity.getEntityId(), nbt);
            return true;
        }
        return false;
    }

    public static boolean tryOpenBlockEditor(boolean nbt) {
        WorldBlock block = Minecraft.getClient().getBlockMouseOver();
        if (block != null) {
            requestOpenBlockEditor(block.getBlockPos(), nbt);
            return true;
        }
        return false;
    }

    public static boolean tryOpenItemEditor(boolean nbt) {
        Item item = Minecraft.getClient().getPlayer().getItemMainHand();
        if (item != null) {
            openItemEditor(item, nbt, IBEEditorClient::updatePlayerMainHandItem);
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
                    openItemEditor(slot.getStack(), keyCode == nbtEditorKey.getKeyCode(), item -> updatePlayerInventoryItem(item, slot.getIndex()));
                } else {
                    WorldBlock block = Minecraft.getClient().getBlockMouseOver();
                    if (block != null) {
                        openItemEditor(slot.getStack(), keyCode == nbtEditorKey.getKeyCode(), item -> updateBlockInventoryItem(item, slot.getIndex(), block.getBlockPos()));
                    }
                }
            }
        }
    }

    public static void openItemEditor(Item item, boolean nbt, Consumer<Item> action) {
        LOGGER.debug(MARKER, "Opening Item Editor (item={};nbt={})", item.get(), nbt);
        if (nbt) {
            EditorHandler.openNBTEditor(item.getTag(), tag -> action.accept(Minecraft.getCommon().createItem(tag)));
        } else {
            EditorHandler.openItemEditor(item, action);
        }
    }

    public static void requestOpenBlockEditor(BlockPos blockPos, boolean nbt) {
        LOGGER.debug(MARKER, "Requesting Block Editor (pos={};nbt={})", blockPos.get(), nbt);
        ClientNetworkEmitter.requestOpenBlockEditor(blockPos, nbt);
    }

    public static void openBlockEditor(Block block, BlockPos blockPos, boolean nbt) {
        LOGGER.debug(MARKER, "Opening Block Editor (pos={};nbt={})", blockPos.get(), nbt);
        if (nbt) {
            EditorHandler.openNBTEditor(block.getData(), tag -> updateBlock(blockPos, Minecraft.getCommon().createBlock(block.getState(), tag)));
        } else {
            EditorHandler.openBlockEditor(block, newBlock -> updateBlock(blockPos, newBlock));
        }
    }

    public static void requestOpenEntityEditor(int entityId, boolean nbt) {
        LOGGER.debug(MARKER, "Requesting Entity Editor (id={};nbt={})", entityId, nbt);
        ClientNetworkEmitter.requestOpenEntityEditor(entityId, nbt);
    }

    public static void openEntityEditor(Entity entity, int entityId, boolean nbt) {
        LOGGER.debug(MARKER, "Opening Entity Editor (id={};nbt={})", entityId, nbt);
        if (nbt) {
            EditorHandler.openNBTEditor(entity.getTag(), tag -> updateEntity(entityId, Minecraft.getCommon().createEntity(tag)));
        } else {
            EditorHandler.openEntityEditor(entity, entity1 -> updateEntity(entityId, entity1));
        }
    }

    public static void requestOpenSelfEditor(boolean nbt) {
        requestOpenEntityEditor(Minecraft.getClient().getPlayer().getEntityId(), nbt);
    }

    public static boolean isServerModInstalled() {
        return serverModInstalled;
    }

    public static void setServerModInstalled(boolean serverModInstalled) {
        LOGGER.debug(MARKER, "Setting 'serverModInstalled' to {}", serverModInstalled);
        IBEEditorClient.serverModInstalled = serverModInstalled;
    }

    private static void updatePlayerMainHandItem(Item item) {
        ClientNetworkEmitter.updatePlayerMainHandItem(item);
        Minecraft.getClient().getScreenHandler().hideScene();
    }

    private static void updatePlayerInventoryItem(Item item, int slotId) {
        ClientNetworkEmitter.updatePlayerInventoryItem(item, slotId);
        Minecraft.getClient().getScreenHandler().hideScene();
    }

    private static void updateBlockInventoryItem(Item item, int slotId, BlockPos blockPos) {
        ClientNetworkEmitter.updateBlockInventoryItem(item, slotId, blockPos);
        Minecraft.getClient().getScreenHandler().hideScene();
    }

    private static void updateBlock(BlockPos blockPos, Block block) {
        ClientNetworkEmitter.updateBlock(blockPos, block);
        Minecraft.getClient().getScreenHandler().hideScene();
    }

    private static void updateEntity(int entityId, Entity entity) {
        ClientNetworkEmitter.updateEntity(entityId, entity);
        Minecraft.getClient().getScreenHandler().hideScene();
    }
}
