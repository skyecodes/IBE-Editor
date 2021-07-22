package com.github.franckyi.ibeeditor.base.client.mvc.base.view.entry;

import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.node.Node;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ActionEditorEntryView extends AbstractEditorEntryView {
    private Button button;

    @Override
    protected Node createContent() {
        return button = button();
    }

    public Button getButton() {
        return button;
    }
}
