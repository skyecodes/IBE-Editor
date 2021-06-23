package com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry;

import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.node.HBox;
import com.github.franckyi.ibeeditor.api.client.mvc.base.view.EditorEntryView;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class ActionEditorEntryView implements EditorEntryView {
    private final HBox root;
    private final Button button;

    public ActionEditorEntryView() {
        root = hBox().add(button = button(), 1);
    }

    @Override
    public HBox getRoot() {
        return root;
    }

    public Button getButton() {
        return button;
    }
}
