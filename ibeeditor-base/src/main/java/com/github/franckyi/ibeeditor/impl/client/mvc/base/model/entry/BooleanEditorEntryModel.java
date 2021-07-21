package com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;

import java.util.function.Consumer;

public class BooleanEditorEntryModel extends ValueEditorEntryModel<Boolean> {
    public BooleanEditorEntryModel(EditorCategoryModel category, Text label, Boolean value, Consumer<Boolean> action) {
        super(category, label, value, action);
    }

    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }
}
