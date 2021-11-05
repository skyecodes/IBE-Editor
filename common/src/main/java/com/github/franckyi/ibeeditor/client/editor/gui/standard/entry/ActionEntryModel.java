package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry;

import com.github.franckyi.ibeeditor.client.editor.gui.standard.CategoryModel;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.EntryModel;
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
