package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.node.Node;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ActionEntryView extends EntryView {
    private Button button;

    @Override
    protected Node createContent() {
        return button = button();
    }

    public Button getButton() {
        return button;
    }
}
