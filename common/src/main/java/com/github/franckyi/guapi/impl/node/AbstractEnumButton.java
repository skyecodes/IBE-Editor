package com.github.franckyi.guapi.impl.node;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.api.event.MouseButtonEvent;
import com.github.franckyi.guapi.api.node.EnumButton;
import com.github.franckyi.minecraft.api.common.text.Text;

import java.util.function.Function;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public abstract class AbstractEnumButton<E extends Enum<E>> extends AbstractButton implements EnumButton<E> {
    private final ObjectProperty<E> valueProperty = DataBindings.getPropertyFactory().createObjectProperty();
    private final ObjectProperty<Function<E, Text>> textFactoryProperty = DataBindings.getPropertyFactory().createObjectProperty(e -> text(e.name()));
    private final E[] values;
    private int index;

    protected AbstractEnumButton(Class<? extends E> enumClass) {
        values = enumClass.getEnumConstants();
        setupTextUpdateListeners();
        updateValue();
    }

    protected AbstractEnumButton(E value) {
        values = value.getDeclaringClass().getEnumConstants();
        for (int i = 0; i < values.length - 1; i++) {
            if (values[i] == value) {
                index = i;
                break;
            }
        }
        setupTextUpdateListeners();
        updateValue();
    }

    @Override
    public ObjectProperty<E> valueProperty() {
        return valueProperty;
    }

    @Override
    public ObjectProperty<Function<E, Text>> textFactoryProperty() {
        return textFactoryProperty;
    }

    @Override
    public E[] getValues() {
        return values;
    }

    @Override
    public void mouseClicked(MouseButtonEvent event) {
        if (event.getButton() == MouseButtonEvent.LEFT_BUTTON) {
            index++;
            if (index >= values.length) {
                index = 0;
            }
        } else if (event.getButton() == MouseButtonEvent.RIGHT_BUTTON) {
            index--;
            if (index < 0) {
                index = values.length - 1;
            }
        }
        updateValue();
    }

    private void setupTextUpdateListeners() {
        valueProperty.addListener(this::updateText);
        textFactoryProperty.addListener(this::updateText);
    }

    private void updateText() {
        setLabel(getTextFactory().apply(getValue()));
    }

    private void updateValue() {
        setValue(values[index]);
    }
}
