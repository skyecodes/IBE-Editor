package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableValue;
import com.github.franckyi.databindings.api.Property;
import com.github.franckyi.databindings.api.event.ObservableValueChangeListener;

import java.util.function.Supplier;

public class ObservedProperty<T> implements ObservableValue<T> {
    private final Supplier<T> valueSupplier;
    private final Property<T> valueProperty;

    public ObservedProperty(Supplier<T> valueSupplier, ObservableValue<?>... triggers) {
        this.valueSupplier = valueSupplier;
        this.valueProperty = ObjectProperty.create(valueSupplier.get());
        for (ObservableValue<?> trigger : triggers) {
            trigger.addListener(this::updateValue);
        }
    }

    private void updateValue() {
        valueProperty.set(valueSupplier.get());
    }

    @Override
    public T get() {
        return valueProperty.get();
    }

    @Override
    public void addListener(ObservableValueChangeListener<? super T> listener) {
        valueProperty.addListener(listener);
    }

    @Override
    public void removeListener(ObservableValueChangeListener<? super T> listener) {
        valueProperty.removeListener(listener);
    }
}
