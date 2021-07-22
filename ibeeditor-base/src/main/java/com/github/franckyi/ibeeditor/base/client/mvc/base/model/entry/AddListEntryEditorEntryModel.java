package com.github.franckyi.ibeeditor.base.client.mvc.base.model.entry;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;

public class AddListEntryEditorEntryModel extends AbstractEditorEntryModel {
    private final Text tooltip;

    public AddListEntryEditorEntryModel(EditorCategoryModel category, Text tooltip) {
        super(category);
        this.tooltip = tooltip;
    }

    public Text getTooltip() {
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
