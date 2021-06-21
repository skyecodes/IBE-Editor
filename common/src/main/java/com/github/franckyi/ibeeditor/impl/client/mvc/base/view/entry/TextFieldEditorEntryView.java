package com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry;

import com.github.franckyi.guapi.api.node.TextField;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public abstract class TextFieldEditorEntryView extends LabeledEditorEntryView {
    protected final TextField textField;

    protected TextFieldEditorEntryView() {
        super();
        root.getChildren().add(textField = textField());
        root.setWeight(textField, 2);
    }

    public TextField getTextField() {
        return textField;
    }
}
