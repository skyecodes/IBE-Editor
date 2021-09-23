package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import net.minecraft.network.chat.Component;

public class AddListEntryEntryModel extends EntryModel {
    private final Component tooltip;

    public AddListEntryEntryModel(CategoryModel category, Component tooltip) {
        super(category);
        this.tooltip = tooltip;
    }

    public Component getTooltip() {
        return tooltip;
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
        return Type.ADD_LIST_ENTRY;
    }
}
