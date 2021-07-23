package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.gameadapter.api.common.text.PlainText;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class TextEntryModel extends ValueEntryModel<PlainText> {
    public TextEntryModel(CategoryModel category, Text label, PlainText value, Consumer<PlainText> action) {
        super(category, label, value == null ? text() : value, action);
    }

    @Override
    public Type getType() {
        return Type.TEXT;
    }
}
