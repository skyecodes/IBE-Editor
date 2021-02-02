package com.github.franckyi.ibeeditor.common;

import com.github.franckyi.guapi.common.GUAPI;
import com.github.franckyi.guapi.common.node.Scene;
import com.github.franckyi.guapi.common.event.ScreenEventType;
import com.github.franckyi.guapi.common.node.Label;
import com.github.franckyi.guapi.common.node.VBox;
import com.github.franckyi.guapi.common.util.Align;
import com.github.franckyi.guapi.common.util.Insets;

public class IBEEditorCommonTest {
    public static void openEditor(String text) {
        try {
            VBox box = new VBox(5);
            Label label = new Label(text);
            label.focusedProperty().addListener(event -> System.out.println("Focus changed!"));
            Label label1 = new Label("+");
            label1.addListener(ScreenEventType.MOUSE_CLICKED, event -> label1.setText(label1.getText() + "-+"));
            label1.setMinWidth(50);
            label1.setMaxWidth(150);
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
