package com.github.franckyi.ibeeditor.base.client.mvc.base.view.entry;

public class TextEditorEntryView extends TextFieldEditorEntryView {
    @Override
    public void build() {
        super.build();
        getTextField().setValidator(s -> true);
    }
}
