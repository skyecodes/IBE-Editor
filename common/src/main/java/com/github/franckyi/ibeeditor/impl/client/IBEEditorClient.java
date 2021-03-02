package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.Screen;
import com.github.franckyi.gamehooks.api.common.*;
import com.github.franckyi.guapi.GUAPI;
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

    public static void init(ClientHooks clientHooks) {
        LOGGER.info(MARKER, "Initializing IBE Editor - client");
        GameHooks.initClient(clientHooks);
        GUAPI.init();
        editorKey = GameHooks.client().registerKeyBinding("ibeeditor.key.editor", GLFW.GLFW_KEY_I, "ibeeditor.category");
        nbtEditorKey = GameHooks.client().registerKeyBinding("ibeeditor.key.nbt_editor", GLFW.GLFW_KEY_N, "ibeeditor.category");
        clipboardKey = GameHooks.client().registerKeyBinding("ibeeditor.key.clipboard", GLFW.GLFW_KEY_J, "ibeeditor.category");
    }

    public static void onKeyInput() {
        if (editorKey.isPressed()) {
            GameHooks.client().unlockCursor();
            openWorldEditor(false);
        } else if (nbtEditorKey.isPressed()) {
            GameHooks.client().unlockCursor();
            openWorldEditor(true);
        } else if (clipboardKey.isPressed()) {
            GameHooks.client().unlockCursor();
            openClipboard();
        }
    }

    public static void openWorldEditor(boolean nbt) {
        if (!(tryOpenEntityEditor(nbt) || tryOpenBlockEditor(nbt) || tryOpenItemEditor(nbt))) {
            requestOpenSelfEditor(nbt);
        }
    }

    public static boolean tryOpenEntityEditor(boolean nbt) {
        WorldEntity entity = GameHooks.client().getEntityMouseOver();
        if (entity != null) {
            requestOpenEntityEditor(entity.getEntityId(), nbt);
            return true;
        }
        return false;
    }

    public static boolean tryOpenBlockEditor(boolean nbt) {
        WorldBlock block = GameHooks.client().getBlockMouseOver();
        if (block != null) {
            requestOpenBlockEditor(block.getBlockPos(), nbt);
            return true;
        }
        return false;
    }

    public static boolean tryOpenItemEditor(boolean nbt) {
        Item item = GameHooks.client().getPlayer().getItemMainHand();
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
                    WorldBlock block = GameHooks.client().getBlockMouseOver();
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
            EditorHandler.openNBTEditor(item.getTag(), tag -> action.accept(GameHooks.common().createItem(tag)));
        } else {
            EditorHandler.openItemEditor(item);
        }
    }

    public static void requestOpenBlockEditor(BlockPos blockPos, boolean nbt) {
        LOGGER.debug(MARKER, "Requesting Block Editor (pos={};nbt={})", blockPos.get(), nbt);
        ClientNetworkEmitter.requestOpenBlockEditor(blockPos, nbt);
    }

    public static void openBlockEditor(Block block, BlockPos blockPos, boolean nbt) {
        LOGGER.debug(MARKER, "Opening Block Editor (pos={};nbt={})", blockPos.get(), nbt);
        if (nbt) {
            EditorHandler.openNBTEditor(block.getData(), tag -> updateBlock(blockPos, GameHooks.common().createBlock(block.getState(), tag)));
        } else {
            //EditorHandler.showBlockEditor(block);
        }
    }

    public static void requestOpenEntityEditor(int entityId, boolean nbt) {
        LOGGER.debug(MARKER, "Requesting Entity Editor (id={};nbt={})", entityId, nbt);
        ClientNetworkEmitter.requestOpenEntityEditor(entityId, nbt);
    }

    public static void openEntityEditor(Entity entity, int entityId, boolean nbt) {
        LOGGER.debug(MARKER, "Opening Entity Editor (id={};nbt={})", entityId, nbt);
        if (nbt) {
            EditorHandler.openNBTEditor(entity.getTag(), tag -> updateEntity(entityId, GameHooks.common().createEntity(tag)));
        } else {
            //EditorHandler.showEntityEditor(entity);
        }
    }

    public static void requestOpenSelfEditor(boolean nbt) {
        requestOpenEntityEditor(GameHooks.client().getPlayer().getEntityId(), nbt);
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
        GameHooks.client().getScreenHandler().hideScene();
    }

    private static void updatePlayerInventoryItem(Item item, int slotId) {
        ClientNetworkEmitter.updatePlayerInventoryItem(item, slotId);
        GameHooks.client().getScreenHandler().hideScene();
    }

    private static void updateBlockInventoryItem(Item item, int slotId, BlockPos blockPos) {
        ClientNetworkEmitter.updateBlockInventoryItem(item, slotId, blockPos);
        GameHooks.client().getScreenHandler().hideScene();
    }

    private static void updateBlock(BlockPos blockPos, Block block) {
        ClientNetworkEmitter.updateBlock(blockPos, block);
        GameHooks.client().getScreenHandler().hideScene();
    }

    private static void updateEntity(int entityId, Entity entity) {
        ClientNetworkEmitter.updateEntity(entityId, entity);
        GameHooks.client().getScreenHandler().hideScene();
    }
}
