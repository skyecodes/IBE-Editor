package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry;

public class TextEntryView extends TextFieldEntryView {
    @Override
    public void build() {
        super.build();
        getTextField().setValidator(s -> true);
    }
}
