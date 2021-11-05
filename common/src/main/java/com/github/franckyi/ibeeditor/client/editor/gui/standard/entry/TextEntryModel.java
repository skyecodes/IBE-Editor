package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry;

import com.github.franckyi.ibeeditor.client.editor.gui.standard.CategoryModel;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class TextEntryModel extends ValueEntryModel<TextComponent> {
    public TextEntryModel(CategoryModel category, MutableComponent label, TextComponent value, Consumer<TextComponent> action) {
        super(category, label, value == null ? text() : value, action);
    }

    @Override
    public Type getType() {
        return Type.TEXT;
    }
}
