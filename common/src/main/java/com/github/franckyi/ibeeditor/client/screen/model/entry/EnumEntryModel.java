package com.github.franckyi.ibeeditor.client.screen.model.entry;

import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import net.minecraft.network.chat.MutableComponent;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

public class EnumEntryModel<E> extends ValueEntryModel<E> {
    private final Collection<? extends E> values;

    public EnumEntryModel(CategoryModel category, MutableComponent label, E[] values, E value, Consumer<E> action) {
        this(category, label, Arrays.asList(values), value, action);
    }

    public EnumEntryModel(CategoryModel category, MutableComponent label, Collection<? extends E> values, E value, Consumer<E> action) {
        super(category, label, value, action);
        this.values = values;
    }

    public Collection<? extends E> getValues() {
        return values;
    }

    @Override
    public Type getType() {
        return Type.ENUM;
    }
}
