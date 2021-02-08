package com.github.franckyi.ibeeditor;

import com.github.franckyi.databindings.api.ObservableIntegerValue;
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
            VBox root = new VBox(5);
            HBox main, top, bottom;
            VBox left, right;
            Button a, b, done, cancel;

            top = new HBox();
            top.getChildren().add(new Label("Title"));
            top.setAlignment(Align.Vertical.CENTER);
            top.setPrefHeight(30);

            bottom = new HBox(20);
            done = new Button("Done");
            done.setPrefWidth(90);
            cancel = new Button("Cancel");
            cancel.setPrefWidth(90);
            bottom.getChildren().addAll(done, cancel);

            ObservableIntegerValue mainHeight = root.heightProperty().substract(top.heightProperty()).substract(bottom.heightProperty()).substract(10);

            main = new HBox(10);
            left = new VBox(5);
            left.setPadding(new Insets(5));
            left.setAlignment(Align.Horizontal.CENTER);
            left.getChildren().addAll(new Label("Category A"), new Label("Category B"), new TextField("Test"), new CheckBox("Check?"));
            left.prefWidthProperty().bind(root.widthProperty().divide(3).substract(5));
            left.prefHeightProperty().bind(mainHeight);
            right = new VBox(5);
            right.setPadding(new Insets(5));
            right.setAlignment(Align.Horizontal.CENTER);
            right.getChildren().addAll(a = new Button("Property C"), b = new Button("Property D"));
            right.prefWidthProperty().bind(root.widthProperty().divide(3).multiply(2).substract(5));
            right.prefHeightProperty().bind(mainHeight);
            main.getChildren().addAll(left, right);

            root.setAlignment(Align.Horizontal.CENTER);
            root.getChildren().addAll(top, main, bottom);

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
            GameHooks.logger().error(MARKER, "Error during test screen initialization", e);
        }
    }
}
