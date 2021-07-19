package com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry;

import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;
import com.github.franckyi.minecraft.api.common.text.Text;

public class ActionEditorEntryModel extends AbstractEditorEntryModel {
    private final Text label;
    private final Runnable action;

    public ActionEditorEntryModel(EditorCategoryModel category, Text label, Runnable action) {
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
