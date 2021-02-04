package com.github.franckyi.ibeeditor;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.client.KeyBindings;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.node.HBox;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.node.Scene;
import com.github.franckyi.guapi.node.VBox;
import com.github.franckyi.guapi.util.Align;
import com.github.franckyi.guapi.util.Insets;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.lwjgl.glfw.GLFW;

public class IBEEditorClient {
    private static KeyBindings.KeyBinding editorKey;
    private static final Marker MARKER = MarkerManager.getMarker("Client");

    public static void init() {
        editorKey = GameHooks.client().keyBindings().register("ibeeditor.key.editor", GLFW.GLFW_KEY_I, "ibeeditor.category");
    }

    public static void tick() {
        if (editorKey.isPressed()) {
            openEditor();
        }
    }

    private static void openEditor() {
        try {
            GameHooks.client().unlockCursor();
            HBox root = new HBox();
            VBox left = new VBox(5);
            left.setPadding(new Insets(5));
            left.setAlignment(Align.Horizontal.CENTER);
            left.getChildren().addAll(new Label("Category A"), new Label("Category B"));
            left.prefWidthProperty().bind(root.widthProperty().divide(3));
            VBox right = new VBox(5);
            right.setPadding(new Insets(5));
            right.setAlignment(Align.Horizontal.CENTER);
            right.getChildren().addAll(new Label("Property A"), new Label("Property B"));
            right.prefWidthProperty().bind(root.widthProperty().divide(3).multiply(2));
            root.getChildren().addAll(left, right);
            Scene scene = new Scene(root);
            scene.setFullScreen(true);
            scene.setPadding(new Insets(5));
            GUAPI.getScreenHandler().show(scene);
        } catch (Exception e) {
            GameHooks.getLogger().error(MARKER, "Error during test screen initialization", e);
        }
    }
}
