package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.ClientHooks;
import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.Screen;
import com.github.franckyi.gamehooks.api.common.*;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.GUAPIFactory;
import com.github.franckyi.guapi.api.ScreenHandler;
import com.github.franckyi.ibeeditor.api.client.mvc.view.*;
import com.github.franckyi.ibeeditor.impl.client.mvc.*;
import com.github.franckyi.ibeeditor.impl.client.mvc.model.EditorModelImpl;
import com.github.franckyi.ibeeditor.impl.common.IBEEditorNetwork;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.lwjgl.glfw.GLFW;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GUAPIFactory.*;
import static com.github.franckyi.ibeeditor.impl.common.IBEEditorCommon.LOGGER;

public final class IBEEditorClient {
    public static KeyBinding editorKey;
    public static KeyBinding nbtEditorKey;
    public static KeyBinding clipboardKey;
    public static final Marker MARKER = MarkerManager.getMarker("Client");
    public static boolean serverModInstalled;

    public static void init(ClientHooks clientHooks, ScreenHandler screenHandler) {
        LOGGER.info(MARKER, "Initializing IBE Editor - client");
        GameHooks.initClient(clientHooks);
        initGUAPI(screenHandler);
        IBEEditorNetwork.initClient();
        editorKey = GameHooks.client().registerKeyBinding("ibeeditor.key.editor", GLFW.GLFW_KEY_I, "ibeeditor.category");
        nbtEditorKey = GameHooks.client().registerKeyBinding("ibeeditor.key.nbt_editor", GLFW.GLFW_KEY_N, "ibeeditor.category");
        clipboardKey = GameHooks.client().registerKeyBinding("ibeeditor.key.clipboard", GLFW.GLFW_KEY_J, "ibeeditor.category");
    }

    private static void initGUAPI(ScreenHandler screenHandler) {
        GUAPI.init(screenHandler);
        GUAPIFactory.registerMVC(EditorView.class, EditorMVCImpl.INSTANCE);
        GUAPIFactory.registerMVC(CategoryView.class, CategoryMVCImpl.INSTANCE);
        GUAPIFactory.registerMVC(StringEntryView.class, StringEntryMVCImpl.INSTANCE);
        GUAPIFactory.registerMVC(IntegerEntryView.class, IntegerEntryMVCImpl.INSTANCE);
        GUAPIFactory.registerMVC(NBTEditorView.class, NBTEditorMVCImpl.INSTANCE);
        GUAPIFactory.registerMVC(TagView.class, TagMVCImpl.INSTANCE);
    }

    public static void tick() {
        if (editorKey.isPressed()) {
            openWorldEditor(false);
        } else if (nbtEditorKey.isPressed()) {
            openWorldEditor(true);
        } else if (clipboardKey.isPressed()) {
            openClipboard();
        }
    }

    public static void openWorldEditor(boolean nbt) {
        GameHooks.client().unlockCursor();
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
            requestOpenBlockEditor(block.getPos(), nbt);
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
        GUAPI.getScreenHandler().showScene(scene(mvc(EditorView.class, new EditorModelImpl()), true, true));
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
                        openItemEditor(slot.getStack(), keyCode == nbtEditorKey.getKeyCode(), item -> updateBlockInventoryItem(item, slot.getIndex(), block.getPos()));
                    }
                }
            }
        }
    }

    public static void openItemEditor(Item item, boolean nbt, Consumer<Item> action) {
        if (nbt) {
            EditorHandler.showNBTEditor(item.getTag(), tag -> action.accept(GameHooks.common().item().fromTag(tag)));
        } else {
            EditorHandler.showItemEditor(item);
        }
    }

    public static void requestOpenBlockEditor(Pos blockPos, boolean nbt) {
        ClientNetwork.requestOpenBlockEditor(blockPos, nbt);
    }

    public static void openBlockEditor(Block block, Pos blockPos, boolean nbt) {
        if (nbt) {
            EditorHandler.showNBTEditor(block.getTag(), tag -> updateBlock(blockPos, tag));
        } else {
            //EditorHandler.showBlockEditor(block);
        }
    }

    public static void requestOpenEntityEditor(int entityId, boolean nbt) {
        ClientNetwork.requestOpenEntityEditor(entityId, nbt);
    }

    public static void openEntityEditor(Entity entity, int entityId, boolean nbt) {
        if (nbt) {
            EditorHandler.showNBTEditor(entity.getTag(), tag -> updateEntity(entityId, tag));
        } else {
            //EditorHandler.showEntityEditor(entity);
        }
    }

    public static void requestOpenSelfEditor(boolean nbt) {
        requestOpenEntityEditor(GameHooks.client().getPlayer().getEntityId(), nbt);
    }

    private static void updatePlayerMainHandItem(Item item) {
        ClientNetwork.updatePlayerMainHandItem(item);
        GameHooks.client().getPlayer().sendMessage(text("Item updated!", GREEN));
        GUAPI.getScreenHandler().hideScene();
    }

    private static void updatePlayerInventoryItem(Item item, int slotId) {
        ClientNetwork.updatePlayerInventoryItem(item, slotId);
        GameHooks.client().getPlayer().sendMessage(text("Item updated!", GREEN));
        GUAPI.getScreenHandler().hideScene();
    }

    private static void updateBlockInventoryItem(Item item, int slotId, Pos blockPos) {
        ClientNetwork.updateBlockInventoryItem(item, slotId, blockPos);
        GameHooks.client().getPlayer().sendMessage(text("Item updated!", GREEN));
        GUAPI.getScreenHandler().hideScene();
    }

    private static void updateBlock(Pos blockPos, ObjectTag tag) {
        ClientNetwork.updateBlock(blockPos, tag);
        GameHooks.client().getPlayer().sendMessage(text("Block updated!", GREEN));
        GUAPI.getScreenHandler().hideScene();
    }

    private static void updateEntity(int entityId, ObjectTag tag) {
        ClientNetwork.updateEntity(entityId, tag);
        GameHooks.client().getPlayer().sendMessage(text("Entity updated!", GREEN));
        GUAPI.getScreenHandler().hideScene();
    }
}
