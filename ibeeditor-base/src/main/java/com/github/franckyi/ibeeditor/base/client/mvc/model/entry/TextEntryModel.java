package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.gameadapter.api.common.text.IPlainText;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.api.GuapiHelper.text;

public class TextEntryModel extends ValueEntryModel<IPlainText> {
    public TextEntryModel(CategoryModel category, IText label, IPlainText value, Consumer<IPlainText> action) {
        super(category, label, value == null ? text() : value, action);
    }

    @Override
    public Type getType() {
        return Type.TEXT;
    }
}
