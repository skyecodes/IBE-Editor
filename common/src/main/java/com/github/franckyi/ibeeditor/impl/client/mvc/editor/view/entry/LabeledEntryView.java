package com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.entry;

import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.view.EntryView;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public abstract class LabeledEntryView implements EntryView {
    protected final HBox root;
    protected Label label;

    protected LabeledEntryView() {
        root = hBox(root -> {
            root.add(label = label().textAlign(CENTER_RIGHT), 1);
            root.spacing(10).align(CENTER);
        });
    }

    public Label getLabel() {
        return label;
    }

    @Override
    public HBox getRoot() {
        return root;
    }
}
