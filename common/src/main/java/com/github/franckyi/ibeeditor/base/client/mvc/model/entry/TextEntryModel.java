package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class TextEntryModel extends ValueEntryModel<TextComponent> {
    private boolean initialized = false;

    public TextEntryModel(CategoryModel category, MutableComponent label, TextComponent value, Consumer<TextComponent> action) {
        super(category, label, value == null ? text() : value, action);
    }

    @Override
    public void setValue(TextComponent value) {
        if (!initialized) {
            initialized = true;
            defaultValue = value;
        }
        super.setValue(value);
    }

    @Override
    public Type getType() {
        return Type.TEXT;
    }
}
