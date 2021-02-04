package com.github.franckyi.ibeeditor;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.client.KeyBindings;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.event.ScreenEventType;
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
        editorKey = GameHooks.keyBindings().register("ibeeditor.key.editor", GLFW.GLFW_KEY_I, "ibeeditor.category");
    }

    public static void tick() {
        if (editorKey.isPressed()) {
            openEditor("Hello world from common code!");
        }
    }

    private static void openEditor(String text) {
        try {
            VBox box = new VBox(5);
            Label label = new Label(text);
            label.focusedProperty().addListener(event -> GameHooks.getLogger().info(MARKER, "Focus changed!"));
            Label label1 = new Label("+");
            label1.addListener(ScreenEventType.MOUSE_CLICKED, event -> label1.setText(label1.getText() + "-+-+"));
            label1.setMinWidth(50);
            label1.setMaxWidth(200);
            label1.setTextAlign(Align.Horizontal.RIGHT);
            box.getChildren().addAll(label, label1, label);
            box.setPadding(new Insets(10));
            box.setAlignment(Align.Horizontal.CENTER);
            GUAPI.getScreenHandler().show(new Scene(box));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
