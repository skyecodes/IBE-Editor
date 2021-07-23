package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;

public class ActionEntryModel extends EntryModel {
    private final Text label;
    private final Runnable action;

    public ActionEntryModel(CategoryModel category, Text label, Runnable action) {
        super(category);
        this.label = label;
        this.action = action;
    }

    public Text getLabel() {
        return label;
    }

    public Runnable getAction() {
        return action;
    }

    @Override
    public boolean isResetable() {
        return false;
    }

    @Override
    public void apply() {
    }

    @Override
    public Type getType() {
        return Type.ACTION;
    }
}
