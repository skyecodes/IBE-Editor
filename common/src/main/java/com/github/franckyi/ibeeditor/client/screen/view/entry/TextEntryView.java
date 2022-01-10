package com.github.franckyi.ibeeditor.client.screen.view.entry;

public class TextEntryView extends TextFieldEntryView {
    @Override
    public void build() {
        super.build();
        getTextField().setValidator(s -> true);
    }
}
