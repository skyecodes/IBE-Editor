package com.github.franckyi.ibeeditor;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.client.KeyBindingHooks;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.event.ScreenEventType;
import com.github.franckyi.guapi.node.*;
import com.github.franckyi.guapi.util.Align;
import com.github.franckyi.guapi.util.Insets;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.lwjgl.glfw.GLFW;

public class IBEEditorClient {
    private static KeyBindingHooks.KeyBinding editorKey;
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
            HBox root;
            VBox left, right;
            Button a, b;
            root = new HBox();

            left = new VBox(5);
            left.setPadding(new Insets(5));
            left.setAlignment(Align.Horizontal.CENTER);
            left.getChildren().addAll(new Label("Category A"), new Label("Category B"), new TextField("Test"));
            left.prefWidthProperty().bind(root.widthProperty().divide(3));

            right = new VBox(5);
            right.setPadding(new Insets(5));
            right.setAlignment(Align.Horizontal.CENTER);
            right.getChildren().addAll(a = new Button("Property C"), b = new Button("Property D"));
            right.prefWidthProperty().bind(root.widthProperty().divide(3).multiply(2));

            root.getChildren().addAll(left, right);

            a.addListener(ScreenEventType.ACTION, event -> {
                a.setDisable(true);
                b.setDisable(false);
            });

            b.addListener(ScreenEventType.ACTION, event -> {
                a.setDisable(false);
                b.setDisable(true);
            });

            Scene scene = new Scene(root, true, true);
            scene.setPadding(new Insets(5));
            GUAPI.getScreenHandler().show(scene);
        } catch (Exception e) {
            GameHooks.getLogger().error(MARKER, "Error during test screen initialization", e);
        }
    }
}
