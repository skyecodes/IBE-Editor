package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

public class TextEditorEntryView extends TextFieldEditorEntryView {
    @Override
    public void build() {
        super.build();
        getTextField().setValidator(s -> true);
    }
}
