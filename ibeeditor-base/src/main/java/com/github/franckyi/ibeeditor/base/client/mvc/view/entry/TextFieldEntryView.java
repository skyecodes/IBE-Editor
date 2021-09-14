package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TextField;

import static com.github.franckyi.guapi.api.GuapiHelper.textField;

public abstract class TextFieldEntryView extends LabeledEntryView {
    private TextField textField;

    @Override
    protected Node createLabeledContent() {
        return textField = textField().prefHeight(16);
    }

    public TextField getTextField() {
        return textField;
    }
}
