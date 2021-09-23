package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TextArea;
import com.github.franckyi.ibeeditor.base.common.ModTexts;
import net.minecraft.network.chat.MutableComponent;

import static com.github.franckyi.guapi.api.GuapiHelper.textArea;

public class SNBTEditorView extends EditorView {
    private TextArea textArea;

    @Override
    protected MutableComponent getHeaderLabelText() {
        return ModTexts.editorTitle("SNBT");
    }

    @Override
    protected Node createEditor() {
        return textArea = textArea();
    }

    public TextArea getTextArea() {
        return textArea;
    }
}
