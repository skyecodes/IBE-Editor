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
    public static KeyBinding clipboardKey;
    public static final Marker MARKER = MarkerManager.getMarker("Client");

    public static void init() {
        editorKey = GameHooks.client().registerKeyBinding("ibeeditor.key.editor", GLFW.GLFW_KEY_I, "ibeeditor.category");
        clipboardKey = GameHooks.client().registerKeyBinding("ibeeditor.key.clipboard", GLFW.GLFW_KEY_J, "ibeeditor.category");
    }

    public static void tick() {
        if (editorKey.isPressed()) {
            openWorldEditor();
        } else if (clipboardKey.isPressed()) {
            openClipboard();
        }
    }

    public static void openWorldEditor() {
        if (!(tryOpenEntityEditor() || tryOpenBlockEditor() || tryOpenItemEditor())) {
            openSelfEditor();
        }
    }

    public static boolean tryOpenEntityEditor() {
        Entity entity = GameHooks.client().entityMouseOver();
        if (entity != null) {
            openEntityEditor(entity);
            return true;
        }
        return false;
    }

    public static boolean tryOpenBlockEditor() {
        Block block = GameHooks.client().blockMouseOver();
        if (block != null) {
            openBlockEditor(block);
            return true;
        }
        return false;
    }

    public static boolean tryOpenItemEditor() {
        Item item = GameHooks.client().player().getItemMainHand();
        if (item != null) {
            openItemEditor(item);
            return true;
        }
        return false;
    }

    public static void openClipboard() {
        GUAPI.getScreenHandler().show(scene(mvc(EditorView.class, new EditorModelImpl()), true, true));
    }

    public static void handleScreenEvent(Screen screen, int keyCode) {
        if (keyCode == editorKey.getKeyCode()) {
            Slot slot = screen.getInventoryFocusedSlot();
            if (slot.hasStack()) {
                if (screen.isPlayerInventoryScreen()) {
                    if (slot.isInPlayerInventory()) {
                        openItemEditorFromPlayerInventory(slot.getId(), slot.getStack());
                    }
                } else {
                    Block block = GameHooks.client().blockMouseOver();
                    if (block != null) {
                        openItemEditorFromBlockInventory(slot.getId(), slot.getStack(), block);
                    }
                }
            }
        }
    }

    public static void openItemEditor(Item item) {
        ItemEditor.build(item);
    }

    public static void openItemEditorFromPlayerInventory(int slotId, Item item) {
        GameHooks.logger().info(IBEEditorClient.MARKER, "PlayerItem " + item);
    }

    public static void openItemEditorFromBlockInventory(int slotId, Item item, Block block) {
        GameHooks.logger().info(IBEEditorClient.MARKER, "BlockItem " + item);
    }

    public static void openBlockEditor(Block block) {
    }

    public static void openEntityEditor(Entity entity) {
    }

    public static void openSelfEditor() {
        openEntityEditor(GameHooks.client().player().getPlayerEntity());
    }
}
