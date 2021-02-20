package com.github.franckyi.ibeeditor.impl.client;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.Screen;
import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.Slot;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.ibeeditor.api.client.mvc.view.EditorView;
import com.github.franckyi.ibeeditor.impl.client.mvc.model.EditorModelImpl;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.lwjgl.glfw.GLFW;

import static com.github.franckyi.guapi.GUAPIFactory.*;

public final class IBEEditorClient {
    public static KeyBinding editorKey;
    public static KeyBinding nbtEditorKey;
    public static KeyBinding clipboardKey;
    public static final Marker MARKER = MarkerManager.getMarker("Client");

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
        Entity entity = GameHooks.client().entityMouseOver();
        if (entity != null) {
            openEntityEditor(entity, nbt);
            return true;
        }
        return false;
    }

    public static boolean tryOpenBlockEditor(boolean nbt) {
        Block block = GameHooks.client().blockMouseOver();
        if (block != null) {
            openBlockEditor(block, nbt);
            return true;
        }
        return false;
    }

    public static boolean tryOpenItemEditor(boolean nbt) {
        Item item = GameHooks.client().player().getItemMainHand();
        if (item != null) {
            openItemEditor(item, nbt);
            return true;
        }
        return false;
    }

    public static void openClipboard() {
        GUAPI.getScreenHandler().show(scene(mvc(EditorView.class, new EditorModelImpl()), true, true));
    }

    public static void handleScreenEvent(Screen screen, int keyCode) {
        if (keyCode == editorKey.getKeyCode() || keyCode == nbtEditorKey.getKeyCode()) {
            Slot slot = screen.getInventoryFocusedSlot();
            if (slot.hasStack()) {
                if (screen.isPlayerInventoryScreen()) {
                    if (slot.isInPlayerInventory()) {
                        openItemEditorFromPlayerInventory(slot.getId(), slot.getStack(), keyCode == nbtEditorKey.getKeyCode());
                    }
                } else {
                    Block block = GameHooks.client().blockMouseOver();
                    if (block != null) {
                        openItemEditorFromBlockInventory(slot.getId(), slot.getStack(), block, keyCode == nbtEditorKey.getKeyCode());
                    }
                }
            }
        }
    }

    public static void openItemEditor(Item item, boolean nbt) {
        if (nbt) {
            NBTEditor.show(item.getTag());
        } else {
            ItemEditor.show(item);
        }
    }

    public static void openItemEditorFromPlayerInventory(int slotId, Item item, boolean nbt) {
        GameHooks.logger().info(IBEEditorClient.MARKER, "PlayerItem " + item);
    }

    public static void openItemEditorFromBlockInventory(int slotId, Item item, Block block, boolean nbt) {
        GameHooks.logger().info(IBEEditorClient.MARKER, "BlockItem " + item);
    }

    public static void openBlockEditor(Block block, boolean nbt) {
    }

    public static void openEntityEditor(Entity entity, boolean nbt) {
        if (nbt) {
            NBTEditor.show(entity.getTag());
        } else {
            //EntityEditor.show(entity);
        }
    }

    public static void openSelfEditor(boolean nbt) {
        openEntityEditor(GameHooks.client().player().getPlayerEntity(), nbt);
    }
}
