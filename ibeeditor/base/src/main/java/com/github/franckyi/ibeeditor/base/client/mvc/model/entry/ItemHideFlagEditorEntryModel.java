package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.EditorCategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.category.ItemHideFlagsEditorCategoryModel;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ItemHideFlagEditorEntryModel extends BooleanEditorEntryModel {
    private final ItemHideFlagsEditorCategoryModel.HideFlag hideFlag;

    public ItemHideFlagEditorEntryModel(EditorCategoryModel category, ItemHideFlagsEditorCategoryModel.HideFlag hideFlag, boolean value, Consumer<Boolean> action) {
        super(category, translated("ibeeditor.gui.editor.item.entry.hide").with(label -> {
            label.with(translated(hideFlag.getName()));
            if (hideFlag == ItemHideFlagsEditorCategoryModel.HideFlag.OTHER) {
                label.extra(text("*"));
            }
        }), value, action);
        this.hideFlag = hideFlag;
    }

    public ItemHideFlagsEditorCategoryModel.HideFlag getHideFlag() {
        return hideFlag;
    }

    @Override
    public Type getType() {
        return Type.HIDE_FLAG;
    }
}
