package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.base.client.mvc.model.EditorCategoryModel;

import java.util.function.Consumer;

public class BooleanEditorEntryModel extends ValueEditorEntryModel<Boolean> {
    public BooleanEditorEntryModel(EditorCategoryModel category, Text label, boolean value, Consumer<Boolean> action) {
        super(category, label, value, action);
    }

    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }
}
