package com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry;

import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.Node;

import static com.github.franckyi.guapi.GuapiHelper.*;

public abstract class LabeledEditorEntryView extends AbstractEditorEntryView {
    private Label label;

    @Override
    public void build() {
        super.build();
        Node labeledContent = createLabeledContent();
        getRight().getChildren().add(0, labeledContent);
        getRight().setWeight(labeledContent, 1);
        getRoot().setWeight(getRight(), 2);
    }

    @Override
    protected Node createContent() {
        return label = label().textAlign(CENTER_RIGHT);
    }

    protected abstract Node createLabeledContent();

    public Label getLabel() {
        return label;
    }
}
