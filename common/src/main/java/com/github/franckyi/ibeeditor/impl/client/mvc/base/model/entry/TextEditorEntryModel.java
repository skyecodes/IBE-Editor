package com.github.franckyi.ibeeditor.impl.client.mvc.base.model.entry;

import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;
import com.github.franckyi.minecraft.api.common.text.PlainText;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public class TextEditorEntryModel extends ValueEditorEntryModel<PlainText> {
    private Supplier<PlainText> valueFactory;

    public TextEditorEntryModel(EditorCategoryModel category, Text label, PlainText value, Consumer<PlainText> action) {
        super(category, label, value == null ? text() : value, action);
    }

    public void setValueFactory(Supplier<PlainText> valueFactory) {
        this.valueFactory = valueFactory;
    }

    @Override
    public void apply() {
        if (valueFactory != null) {
            setValue(valueFactory.get());
        }
        super.apply();
    }

    @Override
    public Type getType() {
        return Type.TEXT;
    }
}
