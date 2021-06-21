package com.github.franckyi.ibeeditor.impl.client.mvc.base.view.entry;

import com.github.franckyi.guapi.util.Predicates;

public class IntegerEditorEntryView extends TextFieldEditorEntryView {
    public IntegerEditorEntryView() {
        getTextField().setValidator(Predicates.IS_INT);
    }
}
