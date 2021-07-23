package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.base.client.mvc.model.EditorCategoryModel;

import java.util.function.Consumer;

public class EnumEditorEntryModel<E extends Enum<E>> extends ValueEditorEntryModel<E> {
    public EnumEditorEntryModel(EditorCategoryModel category, Text label, E value, Consumer<E> action) {
        super(category, label, value, action);
    }

    @Override
    public Type getType() {
        return Type.ENUM;
    }
}
