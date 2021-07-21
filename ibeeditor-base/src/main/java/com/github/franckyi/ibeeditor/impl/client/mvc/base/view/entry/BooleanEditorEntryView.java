package com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry;

import com.github.franckyi.guapi.api.node.CheckBox;
import com.github.franckyi.guapi.api.node.Node;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class BooleanEditorEntryView extends LabeledEditorEntryView {
    private CheckBox checkBox;

    @Override
    protected Node createLabeledContent() {
        return checkBox = checkBox();
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }
}
