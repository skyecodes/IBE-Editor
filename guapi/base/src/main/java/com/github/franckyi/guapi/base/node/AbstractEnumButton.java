package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.guapi.api.event.MouseButtonEvent;
import com.github.franckyi.guapi.api.node.EnumButton;

import java.util.function.Function;

import static com.github.franckyi.guapi.GuapiHelper.*;

public abstract class AbstractEnumButton<E extends Enum<E>> extends AbstractButton implements EnumButton<E> {
    private final ObjectProperty<E> valueProperty = ObjectProperty.create();
    private final IntegerProperty valueIndexProperty = IntegerProperty.create();
    private final ObjectProperty<Function<E, IText>> textFactoryProperty = ObjectProperty.create(e -> text(e.name()));
    private final E[] values;
    private boolean indexUpdated, valueUpdated;

    protected AbstractEnumButton(Class<? extends E> enumClass) {
        values = enumClass.getEnumConstants();
        setupListeners();
        updateValue(0);
    }

    protected AbstractEnumButton(E value) {
        values = value.getDeclaringClass().getEnumConstants();
        setupListeners();
        setValue(value);
    }

    private void setupListeners() {
        valueProperty.addListener(this::updateValueIndex);
        valueIndexProperty.addListener(this::updateValue);
        valueProperty.addListener(this::updateText);
        textFactoryProperty.addListener(this::updateText);
    }

    @Override
    public ObjectProperty<E> valueProperty() {
        return valueProperty;
    }

    @Override
    public IntegerProperty valueIndexProperty() {
        return valueIndexProperty;
    }

    @Override
    public ObjectProperty<Function<E, IText>> textFactoryProperty() {
        return textFactoryProperty;
    }

    @Override
    public E[] getValues() {
        return values;
    }

    @Override
    public void mouseClicked(MouseButtonEvent event) {
        if (event.getButton() == MouseButtonEvent.LEFT_BUTTON) {
            valueIndexProperty().incr();
        } else if (event.getButton() == MouseButtonEvent.RIGHT_BUTTON) {
            valueIndexProperty().decr();
        }
    }

    private void updateValueIndex(E value) {
        valueUpdated = true;
        if (!indexUpdated) {
            for (int i = 0; i < values.length; i++) {
                if (values[i] == value) {
                    setValueIndex(i);
                    break;
                }
            }
        }
        valueUpdated = false;
    }

    private void updateValue(int valueIndex) {
        indexUpdated = true;
        if (valueIndex >= values.length) {
            setValueIndex(0);
        } else if (valueIndex < 0) {
            setValueIndex(values.length - 1);
        } else if (!valueUpdated) {
            setValue(values[valueIndex]);
        }
        indexUpdated = false;
    }

    private void updateText() {
        setLabel(getTextFactory().apply(getValue()));
    }
}
