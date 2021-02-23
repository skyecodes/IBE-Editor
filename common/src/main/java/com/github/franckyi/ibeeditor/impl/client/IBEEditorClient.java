package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.Screen;
import com.github.franckyi.gamehooks.api.common.*;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.ibeeditor.api.client.mvc.view.EditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.model.EditorModelImpl;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.lwjgl.glfw.GLFW;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GUAPIFactory.*;

public final class IBEEditorClient {
    public static KeyBinding editorKey;
    public static KeyBinding nbtEditorKey;
    public static KeyBinding clipboardKey;
    public static final Marker MARKER = MarkerManager.getMarker("Client");
    public static boolean serverModInstalled;

    public static void init() {
        editorKey = GameHooks.client().registerKeyBinding("ibeeditor.key.editor", GLFW.GLFW_KEY_I, "ibeeditor.category");
        nbtEditorKey = GameHooks.client().registerKeyBinding("ibeeditor.key.nbt_editor", GLFW.GLFW_KEY_N, "ibeeditor.category");
        clipboardKey = GameHooks.client().registerKeyBinding("ibeeditor.key.clipboard", GLFW.GLFW_KEY_J, "ibeeditor.category");
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
            openSelfEditor(nbt);
        }
    }

    public static boolean tryOpenEntityEditor(boolean nbt) {
        int entityId = GameHooks.client().entityIdMouseOver();
        if (entityId != -1) {
            requestOpenEntityEditor(entityId, nbt);
            return true;
        }
        return false;
    }

    public static boolean tryOpenBlockEditor(boolean nbt) {
        Pos blockPos = GameHooks.client().blockPosMouseOver();
        if (blockPos != null) {
            requestOpenBlockEditor(blockPos, nbt);
            return true;
        }
        return false;
    }

    public static boolean tryOpenItemEditor(boolean nbt) {
        Item item = GameHooks.client().player().getItemMainHand();
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
                    Pos blockPos = GameHooks.client().blockPosMouseOver();
                    if (blockPos != null) {
                        openItemEditor(slot.getStack(), keyCode == nbtEditorKey.getKeyCode(), item -> updateBlockInventoryItem(item, slot.getIndex(), blockPos));
                    }
                }
            }
        }
    }

    public static void openItemEditor(Item item, boolean nbt, Consumer<Item> action) {
        if (nbt) {
            NBTEditor.show(item.getTag(), tag -> action.accept(GameHooks.common().item().fromTag(tag)));
        } else {
            ItemEditor.show(item);
        }
    }

    public static void requestOpenBlockEditor(Pos block, boolean nbt) {
        ClientNetwork.requestOpenBlockEditor(block, nbt);
    }

    public static void openBlockEditor(Block block, Pos pos, boolean nbt) {
        if (nbt) {
            NBTEditor.show(block.getTag(), tag -> updateBlock(pos, tag));
        } else {
            //BlockEditor.show(block);
        }
    }

    public static void requestOpenEntityEditor(int entityId, boolean nbt) {
        ClientNetwork.requestOpenEntityEditor(entityId, nbt);
    }

    public static void openEntityEditor(Entity entity, int entityId, boolean nbt) {
        if (nbt) {
            NBTEditor.show(entity.getTag(), tag -> updateEntity(entityId, tag));
        } else {
            //EntityEditor.show(entity);
        }
    }

    public static void openSelfEditor(boolean nbt) {
        //openEntityEditor(GameHooks.client().player().getPlayerEntity(), nbt);
    }

    private static void updatePlayerMainHandItem(Item item) {
        ClientNetwork.updatePlayerMainHandItem(item);
        GameHooks.client().player().sendMessage(text("Item updated!", GREEN));
        GUAPI.getScreenHandler().hideScene();
    }

    private static void updatePlayerInventoryItem(Item item, int slotId) {
        ClientNetwork.updatePlayerInventoryItem(item, slotId);
        GameHooks.client().player().sendMessage(text("Item updated!", GREEN));
        GUAPI.getScreenHandler().hideScene();
    }

    private static void updateBlockInventoryItem(Item item, int slotId, Pos pos) {
        ClientNetwork.updateBlockInventoryItem(item, slotId, pos);
        GameHooks.client().player().sendMessage(text("Item updated!", GREEN));
        GUAPI.getScreenHandler().hideScene();
    }

    private static void updateBlock(Pos pos, ObjectTag tag) {
        ClientNetwork.updateBlock(pos, tag);
        GameHooks.client().player().sendMessage(text("Block updated!", GREEN));
        GUAPI.getScreenHandler().hideScene();
    }

    private static void updateEntity(int entityId, ObjectTag tag) {
        ClientNetwork.updateEntity(entityId, tag);
        GameHooks.client().player().sendMessage(text("Entity updated!", GREEN));
        GUAPI.getScreenHandler().hideScene();
    }
}
