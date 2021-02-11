package com.github.franckyi.ibeeditor;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.client.KeyBinding;
import com.github.franckyi.gamehooks.api.client.Screen;
import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.api.common.Entity;
import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.Slot;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.GUAPIFactory;
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
        GUAPI.setDebugMode(true);

        GUAPI.getScreenHandler().show(
            scene(
                vBox(5,
                    hBox(
                        label(text("Editor", AQUA, BOLD), true)
                            .textAlign(CENTER)
                            .prefHeight(20))
                        .align(CENTER),
                    hBox(10,
                        listView(25, "Category A", "Category B", "Category C", "Category D",
                            "Category E", "Category F", "Category G", "Category H",
                            "Category I", "Category J", "Category K", "Category L")
                            .padding(5)
                            .renderer(item -> label(item).textAlign(CENTER)),
                        listView(25, "Property A", "Property B", "Property C", "Property D",
                            "Property E", "Property F", "Property G", "Property H",
                            "Property I", "Property J", "Property K", "Property L")
                            .padding(5)
                            .renderer(item ->
                                hBox(10,
                                    label(item, true),
                                    textField(item))
                                    .align(CENTER)
                                    .weight(0, 1)
                                    .weight(1, 2)))
                        .weight(0, 1)
                        .weight(1, 2)
                        .fillHeight(true),
                    hBox(20,
                        button(text("Done", GREEN))
                            .prefWidth(90)
                            .onClick(e -> GUAPI.getScreenHandler().hide()),
                        button(text("Cancel", RED))
                            .prefWidth(90)
                            .onClick(e -> GUAPI.setDebugMode(!GUAPI.isDebugMode())))
                        .align(CENTER))
                    .align(CENTER)
                    .padding(5)
                    .fillWidth(true)
                    .weight(1, 1),
                true, true
            )
        );

        GUAPI.getScreenHandler().show(scene(scene -> {
            scene.fullScreen(true)
                .texturedBackground(true);
            scene.add(vBox(root -> {
                root.spacing(5)
                    .align(CENTER)
                    .padding(5)
                    .fillWidth(true);
                root.add(label(header -> {
                    header.label("Editor", AQUA, BOLD)
                        .textAlign(CENTER)
                        .prefHeight(20);
                }));
                root.add(hBox(main -> {
                    main.add(listView(String.class, left -> {
                        left.itemHeight(25)
                            .padding(5);
                        left.items("Category A", "Category B", "Category C", "Category D",
                            "Category E", "Category F", "Category G", "Category H",
                            "Category I", "Category J", "Category K", "Category L");
                        left.renderer(item -> label(item).textAlign(CENTER));
                    }), 1);
                    main.add(listView(String.class, right -> {
                        right.itemHeight(25)
                            .padding(5);
                        right.items("Property A", "Property B", "Property C", "Property D",
                            "Property E", "Property F", "Property G", "Property H",
                            "Property I", "Property J", "Property K", "Property L");
                        right.renderer(item -> hBox(prop -> {
                            prop.spacing(10)
                                .align(CENTER)
                                .add(label(item, true), 1)
                                .add(textField(item), 2);
                        }));
                    }), 2);
                    main.spacing(10)
                        .fillHeight(true);
                }), 1);
                root.add(hBox(footer -> {
                    footer.spacing(20)
                        .align(CENTER);
                    footer.children(
                        button(text("Done", GREEN))
                            .prefWidth(90)
                            .onClick(e -> GUAPI.getScreenHandler().hide()),
                        button(text("Cancel", RED))
                            .prefWidth(90)
                            .onClick(e -> GUAPI.setDebugMode(!GUAPI.isDebugMode()))
                    );
                }));
            }));
        }));
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
