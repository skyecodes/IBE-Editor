package com.github.franckyi.ibeeditor.client.screen.view.selection.color;

import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.Node;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class TextColorSelectionScreenView extends ColorSelectionScreenView {
    private Label exampleLabel;

    @Override
    protected Node createExample() {
        return exampleLabel = label().textAlign(CENTER);
    }

    public Label getExampleLabel() {
        return exampleLabel;
    }
}
