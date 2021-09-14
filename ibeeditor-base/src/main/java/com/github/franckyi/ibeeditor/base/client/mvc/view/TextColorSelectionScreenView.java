package com.github.franckyi.ibeeditor.base.client.mvc.view;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.guapi.api.node.Node;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class TextColorSelectionScreenView extends ColorSelectionScreenView {
    private IText exampleText;

    @Override
    protected Node createExample() {
        exampleText = text("Test ").extra(text("Test").bold(), text(" Test").italic());
        return label(exampleText).tooltip(exampleText).textAlign(CENTER);
    }

    public IText getExampleText() {
        return exampleText;
    }
}
