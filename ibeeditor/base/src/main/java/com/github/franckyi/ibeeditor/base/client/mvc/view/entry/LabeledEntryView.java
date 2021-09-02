package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.Node;

import static com.github.franckyi.guapi.GuapiHelper.*;

public abstract class LabeledEntryView extends EntryView {
    private Label label;
    private Node labeledContent;

    @Override
    public void build() {
        super.build();
        labeledContent = createLabeledContent();
        getRight().getChildren().add(0, labeledContent);
        getRight().setWeight(labeledContent, 1);
        getRoot().setWeight(getRight(), 2);
    }

    @Override
    protected Node createContent() {
        return label = label().padding(right(5)).textAlign(CENTER_RIGHT);
    }

    protected abstract Node createLabeledContent();

    public Label getLabel() {
        return label;
    }

    public Node getLabeledContent() {
        return labeledContent;
    }
}
