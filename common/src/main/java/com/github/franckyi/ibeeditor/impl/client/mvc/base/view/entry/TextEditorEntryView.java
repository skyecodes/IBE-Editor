package com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry;

public class TextEditorEntryView extends TextFieldEditorEntryView {
    public TextEditorEntryView() {
        getTextField().setValidator(s -> true);
    }
}
