package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

import com.github.franckyi.guapi.api.util.Predicates;

public class IntegerEditorEntryView extends TextFieldEditorEntryView {
    @Override
    public void build() {
        super.build();
        getTextField().setValidator(Predicates.IS_INT);
    }
}
