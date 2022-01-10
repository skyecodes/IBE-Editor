package com.github.franckyi.ibeeditor.client.screen.view.entry;

import com.github.franckyi.guapi.api.util.Predicates;

public class IntegerEntryView extends TextFieldEntryView {
    @Override
    public void build() {
        super.build();
        getTextField().setValidator(Predicates.IS_INT);
    }
}
