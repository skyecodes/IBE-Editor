package com.github.franckyi.ibeeditor.impl.client.mvc.model;

import com.github.franckyi.gamehooks.util.common.Text;
import com.github.franckyi.ibeeditor.api.client.mvc.model.EntryModel;

public abstract class AbstractEntryModel implements EntryModel {
    private final Text label;

    protected AbstractEntryModel(Text label) {
        this.label = label;
    }

    @Override
    public Text getLabel() {
        return label;
    }
}
