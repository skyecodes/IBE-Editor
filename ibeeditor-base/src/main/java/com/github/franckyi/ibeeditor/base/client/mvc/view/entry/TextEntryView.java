package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

public class TextEntryView extends TextFieldEntryView {
    @Override
    public void build() {
        super.build();
        getTextField().setValidator(s -> true);
    }
}
