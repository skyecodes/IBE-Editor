package com.github.franckyi.ibeeditor.client.screen.view;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TextArea;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.ModTextures;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.network.chat.MutableComponent;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class SNBTEditorView extends ScreenView {
    private TextArea textArea;
    private TexturedButton formatButton;

    public SNBTEditorView() {
        super();
    }

    @Override
    protected MutableComponent getHeaderLabelText() {
        return ModTexts.editorTitle("SNBT");
    }

    @Override
    protected Node createButtonBar() {
        Node res = super.createButtonBar();
        buttonBarLeft.getChildren().addAll(hBox(2, formatButton = createButton(ModTextures.FORMAT, ModTexts.FORMAT)));
        return res;
    }

    @Override
    protected Node createEditor() {
        return textArea = textArea();
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public TexturedButton getFormatButton() {
        return formatButton;
    }
}
