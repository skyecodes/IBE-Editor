package com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.entry;

import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.TexturedButton;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class TextEntryView extends TextFieldEntryView {
    protected final HBox fieldButtonBox;
    protected final TexturedButton resetButton;

    public TextEntryView() {
        root.resetWeight(textField);
        root.getChildren().remove(textField);
        resetButton = texturedButton("ibeeditor:textures/gui/text_convert.png", 16, 32,
                false).prefSize(16, 16).imageY(16).tooltip(text("Reset to item's default"));
        fieldButtonBox = hBox(10, textField, resetButton);
        root.getChildren().add(fieldButtonBox);
        root.setWeight(fieldButtonBox, 2);
    }

    public TexturedButton getResetButton() {
        return resetButton;
    }
}
