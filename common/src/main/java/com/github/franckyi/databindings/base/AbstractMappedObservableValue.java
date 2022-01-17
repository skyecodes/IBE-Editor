package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableValue;
import com.github.franckyi.databindings.api.event.ObservableValueChangeListener;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public abstract class AbstractMappedObservableValue<T, S> implements ObservableValue<T> {
    private final BiConsumer<ObjectProperty<T>, S> updater;
    private final Supplier<? extends S> valueSupplier;
    private final ObjectProperty<T> valueProperty = ObjectProperty.create();

    public AbstractMappedObservableValue(BiConsumer<ObjectProperty<T>, S> updater, Supplier<? extends S> valueSupplier, ObservableValue<?>... triggers) {
        this.updater = updater;
        this.valueSupplier = valueSupplier;
        Arrays.stream(triggers).forEach(trigger -> trigger.addListener(this::updateValue));
        updateValue();
    }

    private void updateValue() {
        updater.accept(valueProperty, valueSupplier.get());
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
