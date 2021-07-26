package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.node.Node;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class TextColorSelectionScreenView extends ColorSelectionScreenView {
    private Text exampleText;

    @Override
    protected Node createExample() {
        exampleText = text("Test ").extra(text("Test").bold(), text(" Test").italic());
        return label(exampleText).tooltip(exampleText).textAlign(CENTER);
    }

    public Text getExampleText() {
        return exampleText;
    }
}
