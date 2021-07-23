package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

public class ItemHideFlagEditorEntryView extends BooleanEditorEntryView {
    @Override
    public void build() {
        super.build();
        getRoot().setWeight(getLabel(), 3);
    }
}
