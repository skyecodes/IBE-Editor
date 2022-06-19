package com.github.franckyi.ibeeditor.client.screen.model.entry;

import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class TextEntryModel extends ValueEntryModel<MutableComponent> {
    public TextEntryModel(CategoryModel category, MutableComponent label, MutableComponent value, Consumer<MutableComponent> action) {
        super(category, label, value == null ? text() : value, action);
    }

    @Override
    public Type getType() {
        return Type.TEXT;
    }

    public void resetDefaultValue() {
        defaultValue = getValue();
    }
}
