package com.github.franckyi.ibeeditor;

import com.github.franckyi.databindings.api.ObservableIntegerValue;
import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.Screen;
import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.Slot;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.ListView;
import com.github.franckyi.guapi.api.node.VBox;
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
        VBox root;
        HBox main, top, bottom;
        ListView<String> left, right;
        GUAPI.getScreenHandler().show(
                scene(
                        root = vBox(5,
                                top = hBox(
                                        label(text("Editor", AQUA, BOLD)).textAlign(CENTER).prefHeight(20)
                                ),
                                main = weightedHBox(10,
                                        left = listView(25, "Category A", "Category B", "Category C", "Category D",
                                                "Category E", "Category F", "Category G", "Category H",
                                                "Category I", "Category J", "Category K", "Category L")
                                                .padding(5).renderer(s -> label(s).textAlign(CENTER)),
                                        right = listView(25, "Property A", "Property B", "Property C", "Property D",
                                                "Property E", "Property F", "Property G", "Property H",
                                                "Property I", "Property J", "Property K", "Property L")
                                                .padding(5).renderer(
                                                        s -> weightedHBox(20, label(s), textField(s))
                                                                .align(CENTER_V)
                                                                .weight(1, 2)
                                                )
                                ).weight(1, 2),
                                bottom = hBox(20,
                                        button(text("Done", GREEN)).prefWidth(90).onClick(e -> GUAPI.getScreenHandler().hide()),
                                        button(text("Cancel", RED)).prefWidth(90).onClick(e -> GUAPI.getScreenHandler().hide())
                                )
                        ).align(CENTER_H).padding(5),
                        true, true
                ).with(scene -> {
                    ObservableIntegerValue mainHeight = root.heightProperty().substract(top.heightProperty()).substract(bottom.heightProperty()).substract(20);
                    main.prefWidthProperty().bind(root.widthProperty().substract(10));
                    main.setRenderPriority(1);
                    left.prefHeightProperty().bind(mainHeight);
                    right.prefHeightProperty().bind(mainHeight);
                    right.baseXProperty().bind(right.xProperty());
                    left.fullWidthProperty().bind(scene.widthProperty());
                    left.fullHeightProperty().bind(scene.heightProperty());
                    right.fullWidthProperty().bind(scene.widthProperty());
                    right.fullHeightProperty().bind(scene.heightProperty());
                })
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
