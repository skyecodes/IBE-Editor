package com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.entry;

import com.github.franckyi.guapi.util.Predicates;

public class IntegerEntryView extends TextFieldEntryView {
    public IntegerEntryView() {
        getTextField().setValidator(Predicates.IS_INT);
    }
}
