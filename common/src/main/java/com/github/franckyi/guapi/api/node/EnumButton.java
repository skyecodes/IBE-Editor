package com.github.franckyi.guapi.api.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;
import net.minecraft.network.chat.Component;

import java.util.function.Function;

public interface EnumButton<E> extends Button {
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

    default Function<E, Component> getTextFactory() {
        return textFactoryProperty().getValue();
    }

    ObjectProperty<Function<E, Component>> textFactoryProperty();

    default void setTextFactory(Function<E, Component> value) {
        textFactoryProperty().setValue(value);
    }

    ObservableList<E> getValues();
}
