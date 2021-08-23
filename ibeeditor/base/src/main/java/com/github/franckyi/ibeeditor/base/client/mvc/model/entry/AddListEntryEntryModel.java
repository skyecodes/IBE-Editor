package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;

public class AddListEntryEntryModel extends EntryModel {
    private final IText tooltip;

    public AddListEntryEntryModel(CategoryModel category, IText tooltip) {
        super(category);
        this.tooltip = tooltip;
    }

    public IText getTooltip() {
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
