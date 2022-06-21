package com.github.franckyi.ibeeditor.client.screen.model.entry;

import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Consumer;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class TextEntryModel extends ValueEntryModel<MutableComponent> {
    private Runnable onApply;

    public TextEntryModel(CategoryModel category, MutableComponent label, MutableComponent value, Consumer<MutableComponent> action) {
        super(category, label, value == null ? text() : value, action);
    }

    public Runnable getOnApply() {
        return onApply;
    }

    public void setOnApply(Runnable onApply) {
        this.onApply = onApply;
    }

    @Override
    public void apply() {
        if (onApply != null) {
            onApply.run();
        }
        super.apply();
    }

    @Override
    public Type getType() {
        return Type.TEXT;
    }

    public void resetDefaultValue() {
        defaultValue = getValue();
    }
}
