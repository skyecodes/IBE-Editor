package com.github.franckyi.ibeeditor;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.Screen;
import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.Slot;
import com.github.franckyi.gamehooks.api.common.text.Text;
import com.github.franckyi.gamehooks.api.common.text.TextFormatting;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.lwjgl.glfw.GLFW;

public class IBEEditorClient {
    public static KeyBinding editorKey;
    public static KeyBinding clipboardKey;
    public static final Marker MARKER = MarkerManager.getMarker("Client");

    public static void init() {
        editorKey = GameHooks.client().registerKeyBinding("ibeeditor.key.editor", GLFW.GLFW_KEY_I, "ibeeditor.category");
        clipboardKey = GameHooks.client().registerKeyBinding("ibeeditor.key.clipboard", GLFW.GLFW_KEY_J, "ibeeditor.category");
    }

    public static void tick() {
        if (editorKey.isPressed()) {
            openEditor();
        } else if (clipboardKey.isPressed()) {
            openClipboard();
        }
    }

    public static boolean openEditor() {
        return openEntityEditor() || openBlockEditor() || openItemEditor() || openSelfEditor();
    }

    public static boolean openEntityEditor() {
        Entity entity = GameHooks.client().entityMouseOver();
        if (entity != null) {
            GameHooks.logger().info(MARKER, "Entity " + entity);
            return true;
        }
        return false;
    }

    public static boolean openBlockEditor() {
        Block block = GameHooks.client().blockMouseOver();
        if (block != null) {
            GameHooks.logger().info(MARKER, "Block " + block);
            return true;
        }
        return false;
    }

    public static boolean openItemEditor() {
        Item item = GameHooks.client().player().getItemMainHand();
        if (item != null) {
            GameHooks.logger().info(MARKER, "Item " + item);
            return true;
        }
        return false;
    }

    public static boolean openSelfEditor() {
        GameHooks.logger().info(MARKER, "Self " + GameHooks.client().player().getPlayerEntity());
        return true;
    }

    public static void openClipboard() {
        GameHooks.logger().info(IBEEditorClient.MARKER, "Clipboard");
    }

    public static void handleScreenEvent(Screen screen, int keyCode) {
        if (keyCode == editorKey.getKeyCode()) {
            Slot slot = screen.getInventoryFocusedSlot();
            if (slot.hasStack()) {
                if (screen.isPlayerInventoryScreen()) {
                    if (slot.isPlayerInventory()) {
                        openItemEditorFromInventoryScreen(slot.getId(), slot.getStack());
                    }
                } else {
                    Block block = GameHooks.client().blockMouseOver();
                    if (block != null) {
                        openItemEditorFromBlockScreen(slot.getId(), slot.getStack(), block);
                    }
                }
            }
        }
    }

    public static void openItemEditorFromInventoryScreen(int id, Item item) {
        GameHooks.logger().info(IBEEditorClient.MARKER, "PlayerItem " + item);
    }

    public static void openItemEditorFromBlockScreen(int id, Item item, Block block) {
        GameHooks.logger().info(IBEEditorClient.MARKER, "BlockItem " + item);
    }
}
