package com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TextField;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public abstract class TextFieldEditorEntryView extends LabeledEditorEntryView {
    private TextField textField;

    @Override
    protected Node createLabeledContent() {
        return textField = textField().prefHeight(16);
    }

    public TextField getTextField() {
        return textField;
    }
}
