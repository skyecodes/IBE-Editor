package com.github.franckyi.ibeeditor.client.screen.model.entry;

import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import net.minecraft.network.chat.Component;

public class ActionEntryModel extends EntryModel {
    private final Component label;
    private final Runnable action;

    public ActionEntryModel(CategoryModel category, Component label, Runnable action) {
        super(category);
        this.label = label;
        this.action = action;
    }

    public Component getLabel() {
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
