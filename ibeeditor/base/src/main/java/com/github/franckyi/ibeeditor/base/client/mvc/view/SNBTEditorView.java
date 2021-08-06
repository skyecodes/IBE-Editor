package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TextArea;
import com.github.franckyi.ibeeditor.base.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.base.client.ModTextures;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class SNBTEditorView extends EditorView {
    private TextArea textArea;

    @Override
    protected Node createHeader() {
        return hBox(header -> {
            header.add(hBox().prefWidth(16));
            header.add(label(ModTexts.editorTitle("SNBT"), true).textAlign(CENTER).prefHeight(20), 1);
            header.add(createButton(ModTextures.SETTINGS, ModTexts.SETTINGS).action(ModScreenHandler::openSettingsScreen));
            header.align(CENTER);
        });
    }

    @Override
    protected Node createEditor() {
        return textArea = textArea();
    }

    public TextArea getTextArea() {
        return textArea;
    }
}
