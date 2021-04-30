package com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.entry;

import com.github.franckyi.guapi.api.node.TexturedButton;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class TextEntryView extends TextFieldEntryView {
    private final TexturedButton convertButton;

    public TextEntryView() {
        convertButton = texturedButton("ibeeditor:textures/gui/text_convert.png", 16, 32,
                false).prefSize(16, 16).tooltip(text("Edit"))
                .action(this::switchConvertButton);
        //root.getChildren().add(convertButton);
    }

    public TexturedButton getConvertButton() {
        return convertButton;
    }

    public void switchConvertButton() {
        getConvertButton().setImageY(getConvertButton().getImageX() == 0 ? 16 : 0);
    }
}
