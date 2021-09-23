package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.api.GuapiHelper.text;

public class TextEntryModel extends ValueEntryModel<TextComponent> {
    public TextEntryModel(CategoryModel category, MutableComponent label, TextComponent value, Consumer<TextComponent> action) {
        super(category, label, value == null ? text() : value, action);
    }

    @Override
    public Type getType() {
        return Type.TEXT;
    }
}
