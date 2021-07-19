package com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry;

import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;
import com.github.franckyi.minecraft.api.common.text.PlainText;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class TextEditorEntryModel extends ValueEditorEntryModel<PlainText> {
    public TextEditorEntryModel(EditorCategoryModel category, Text label, PlainText value, Consumer<PlainText> action) {
        super(category, label, value == null ? text() : value, action);
    }

    @Override
    public Type getType() {
        return Type.TEXT;
    }
}
