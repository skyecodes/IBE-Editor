package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

import com.github.franckyi.guapi.api.node.CheckBox;
import com.github.franckyi.guapi.api.node.Node;

import static com.github.franckyi.guapi.api.GuapiHelper.checkBox;
import static com.github.franckyi.guapi.api.GuapiHelper.hBox;

public class BooleanEntryView extends LabeledEntryView {
    private CheckBox checkBox;

    @Override
    protected Node createLabeledContent() {
        return hBox(checkBox = checkBox());
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }
}
