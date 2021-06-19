package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.function.Function;

public interface EnumButton<E extends Enum<E>> extends Button {
    default E getValue() {
        return valueProperty().getValue();
    }

    ObjectProperty<E> valueProperty();

    default void setValue(E value) {
        valueProperty().setValue(value);
    }

    default Function<E, Text> getTextFactory() {
        return textFactoryProperty().getValue();
    }

    ObjectProperty<Function<E, Text>> textFactoryProperty();

    default void setTextFactory(Function<E, Text> value) {
        textFactoryProperty().setValue(value);
    }

    E[] getValues();
}
