package com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry;

import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.ibeeditor.api.client.mvc.base.view.EditorEntryView;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public abstract class LabeledEditorEntryView implements EditorEntryView {
    protected final HBox root;
    protected Label label;

    protected LabeledEditorEntryView() {
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
