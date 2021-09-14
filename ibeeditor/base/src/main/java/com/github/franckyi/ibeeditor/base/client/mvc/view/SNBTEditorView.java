package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TextArea;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

import static com.github.franckyi.guapi.GuapiHelper.textArea;

public class SNBTEditorView extends EditorView {
    private TextArea textArea;

    @Override
    protected IText getHeaderLabelText() {
        return ModTexts.editorTitle("SNBT");
    }

    @Override
    protected Node createEditor() {
        return textArea = textArea().wrapText();
    }

    public TextArea getTextArea() {
        return textArea;
    }
}
