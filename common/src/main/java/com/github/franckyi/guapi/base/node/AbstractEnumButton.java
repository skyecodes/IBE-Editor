package com.github.franckyi.guapi.base.node;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.guapi.api.event.MouseButtonEvent;
import com.github.franckyi.guapi.api.node.EnumButton;
import net.minecraft.network.chat.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

import static com.github.franckyi.guapi.api.GuapiHelper.text;

public abstract class AbstractEnumButton<E> extends AbstractButton implements EnumButton<E> {
    private final ObjectProperty<E> valueProperty = ObjectProperty.create();
    private final IntegerProperty valueIndexProperty = IntegerProperty.create();
    private final ObjectProperty<Function<E, Component>> textFactoryProperty = ObjectProperty.create(e -> text(e.toString()));
    private final ObservableList<E> values;
    private boolean indexUpdated, valueUpdated;

    protected AbstractEnumButton() {
        this(Collections.emptyList());
    }

    protected AbstractEnumButton(E[] values) {
        this(Arrays.asList(values));
    }

    protected AbstractEnumButton(Collection<? extends E> values) {
        this(values, null);
    }

    protected AbstractEnumButton(E[] values, E value) {
        this(Arrays.asList(values), value);
    }

    protected AbstractEnumButton(Collection<? extends E> values, E value) {
        this.values = ObservableList.create(values);
        valueProperty.addListener(this::updateIndexFromValue);
        valueIndexProperty.addListener(this::updateValueFromIndex);
        this.values.addListener(() -> updateValueFromIndex(getValueIndex()));
        valueProperty.addListener(this::updateText);
        textFactoryProperty.addListener(this::updateText);
        if (value == null) {
            updateValueFromIndex(0);
        } else {
            setValue(value);
        }
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
    public ObjectProperty<Function<E, Component>> textFactoryProperty() {
        return textFactoryProperty;
    }

    @Override
    public ObservableList<E> getValues() {
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

    private void updateIndexFromValue(E value) {
        valueUpdated = true;
        if (!indexUpdated) {
            for (int i = 0; i < values.size(); i++) {
                if (values.get(i) == value) {
                    setValueIndex(i);
                    break;
                }
            }
        }
        valueUpdated = false;
    }

    private void updateValueFromIndex(int valueIndex) {
        indexUpdated = true;
        if (valueIndex >= values.size()) {
            setValueIndex(0);
        } else if (valueIndex < 0) {
            setValueIndex(values.size() - 1);
        } else if (!valueUpdated) {
            setValue(values.get(valueIndex));
        }
        indexUpdated = false;
    }

    private void updateText() {
        setLabel(getTextFactory().apply(getValue()));
    }
}
