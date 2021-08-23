package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.text.IText;

import java.util.function.Function;

public interface EnumButton<E extends Enum<E>> extends Button {
    default E getValue() {
        return valueProperty().getValue();
    }

    ObjectProperty<E> valueProperty();

    default void setValue(E value) {
        valueProperty().setValue(value);
    }

    default int getValueIndex() {
        return valueIndexProperty().getValue();
    }

    IntegerProperty valueIndexProperty();

    default void setValueIndex(int value) {
        valueIndexProperty().setValue(value);
    }

    default Function<E, IText> getTextFactory() {
        return textFactoryProperty().getValue();
    }

    ObjectProperty<Function<E, IText>> textFactoryProperty();

    default void setTextFactory(Function<E, IText> value) {
        textFactoryProperty().setValue(value);
    }

    E[] getValues();
}
