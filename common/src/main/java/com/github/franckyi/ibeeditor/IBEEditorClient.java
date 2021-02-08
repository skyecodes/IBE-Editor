package com.github.franckyi.ibeeditor;

import com.github.franckyi.databindings.api.ObservableIntegerValue;
import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.client.KeyBindingHooks;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.node.*;
import com.github.franckyi.guapi.util.Align;
import com.github.franckyi.guapi.util.Insets;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.lwjgl.glfw.GLFW;

public class IBEEditorClient {
    private static KeyBindingHooks.KeyBinding editorKey;
    private static KeyBindingHooks.KeyBinding testingKey;
    private static final Marker MARKER = MarkerManager.getMarker("Client");

    public static void init() {
        editorKey = GameHooks.client().keyBindings().register("ibeeditor.key.editor", GLFW.GLFW_KEY_I, "ibeeditor.category");
        testingKey = GameHooks.client().keyBindings().register("Testing", GLFW.GLFW_KEY_J, "ibeeditor.category");
    }

    public static void tick() {
        if (editorKey.isPressed()) {
            openEditor();
        } else if (testingKey.isPressed()) {
            openTesting();
        }
    }

    private static void openTesting() {
        try {
            GameHooks.client().unlockCursor();
            VBox root = new VBox();
            HBox b1 = new HBox();
            VBox b2 = new VBox(10);
            HBox b3 = new HBox(5);
            HBox b4 = new HBox(5);
            TextField t1 = new TextField("Test 1");
            TextField t2 = new TextField("Test 2");
            TextField t3 = new TextField("Test 3");
            TextField t4 = new TextField("Test 4");
            root.getChildren().add(b1);
            b1.getChildren().add(b2);
            b2.getChildren().addAll(b3, b4);
            b3.getChildren().addAll(t1, t2);
            b4.getChildren().addAll(t3, t4);
            Scene scene = new Scene(root);
            scene.setPadding(new Insets(10));
            GUAPI.getScreenHandler().show(scene);
        } catch (Exception e) {
            GameHooks.logger().error(MARKER, "Error during test screen initialization", e);
        }
    }

    private static void openEditor() {
        try {
            GameHooks.client().unlockCursor();
            VBox root = new VBox(5);
            HBox main, top, bottom;
            ListView<String> left, right;
            Button done, cancel;

            top = new HBox();
            top.getChildren().add(new Label("Editor"));
            top.setAlignment(Align.Vertical.CENTER);
            top.setPrefHeight(20);

            bottom = new HBox(20);
            done = new Button("Done");
            done.setPrefWidth(90);
            cancel = new Button("Cancel");
            cancel.setPrefWidth(90);
            bottom.getChildren().addAll(done, cancel);

            ObservableIntegerValue mainHeight = root.heightProperty().substract(top.heightProperty()).substract(bottom.heightProperty()).substract(20);

            main = new HBox(10);
            left = new ListView<>(25);
            left.setPadding(new Insets(5));
            left.getItems().addAll(
                    "Category A", "Category B", "Category C", "Category D",
                    "Category E", "Category F", "Category G", "Category H",
                    "Category I", "Category J", "Category K", "Category L"
            );
            left.setRenderer(s -> {
                Label text = new Label(s);
                text.setTextAlign(Align.CENTER);
                return text;
            });
            left.prefWidthProperty().bind(root.widthProperty().divide(3).substract(10));
            left.prefHeightProperty().bind(mainHeight);
            right = new ListView<>(25);
            right.setPadding(new Insets(5));
            right.getItems().addAll(
                    "Property A", "Property B", "Property C", "Property D",
                    "Property E", "Property F", "Property G", "Property H",
                    "Property I", "Property J", "Property K", "Property L"
            );
            right.setRenderer(s -> {
                HBox box = new HBox(20);
                box.getChildren().add(new Label(s));
                box.getChildren().add(new TextField(s));
                box.setAlignment(Align.Vertical.CENTER);
                return box;
            });
            right.prefWidthProperty().bind(root.widthProperty().divide(3).multiply(2).substract(10));
            right.prefHeightProperty().bind(mainHeight);
            right.baseXProperty().bind(right.xProperty());
            main.getChildren().addAll(left, right);
            main.setRenderPriority(1);

            root.setAlignment(Align.Horizontal.CENTER);
            root.getChildren().addAll(top, main, bottom);
            root.setPadding(new Insets(5));

            Scene scene = new Scene(root, true, true);
            left.fullWidthProperty().bind(scene.widthProperty());
            left.fullHeightProperty().bind(scene.heightProperty());
            right.fullWidthProperty().bind(scene.widthProperty());
            right.fullHeightProperty().bind(scene.heightProperty());
            GUAPI.getScreenHandler().show(scene);
        } catch (Exception e) {
            GameHooks.logger().error(MARKER, "Error during test screen initialization", e);
        }
    }
}
