package com.github.franckyi.ibeeditor;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.Screen;
import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.Slot;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.api.node.WeightedHBox;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.lwjgl.glfw.GLFW;

import static com.github.franckyi.guapi.GUAPIFactory.*;

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
        WeightedHBox main;
        GUAPI.getScreenHandler().show(
                scene(
                        vBox(5,
                                hBox(
                                        label(text("Editor", AQUA, BOLD), true).textAlign(CENTER).prefHeight(20)
                                ).align(CENTER),
                                main = weightedHBox(10,
                                        listView(25, "Category A", "Category B", "Category C", "Category D",
                                                "Category E", "Category F", "Category G", "Category H",
                                                "Category I", "Category J", "Category K", "Category L")
                                                .padding(5).renderer(s -> label(s).textAlign(CENTER)),
                                        listView(25, "Property A", "Property B", "Property C", "Property D",
                                                "Property E", "Property F", "Property G", "Property H",
                                                "Property I", "Property J", "Property K", "Property L")
                                                .padding(5).renderer(
                                                s -> weightedHBox(10, label(s, true), textField(s))
                                                        .align(CENTER_LEFT)
                                                        .weight(1, 2)
                                        )
                                ).weight(1, 2).fillHeight(true),
                                hBox(20,
                                        button(text("Done", GREEN)).prefWidth(90).onClick(e -> GUAPI.getScreenHandler().hide()),
                                        button(text("Cancel", RED)).prefWidth(90).onClick(e -> GUAPI.setDebugMode(!GUAPI.isDebugMode()))
                                ).align(CENTER)
                        ).align(CENTER).padding(5).fillWidth(true).with(it -> main.prefHeightProperty().bind(it.heightProperty().substract(60))), // todo Vgrow for VBox / Hgrow for HBox
                        true, true
                )
        );
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
