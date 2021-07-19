package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.Property;
import com.github.franckyi.databindings.api.event.ObservableValueChangeListener;
import com.github.franckyi.databindings.api.ObservableValue;

import java.util.function.BiFunction;

public abstract class AbstractBiMappedObservableValue<T, X, Y> implements ObservableValue<Y>, ObservableValueChangeListener<T> {
    private final ObservableValueChangeListener<? super T> aListener;
    private final ObservableValueChangeListener<? super X> bListener;
    private final ObservableValue<T> a;
    private final ObservableValue<X> b;
    private final BiFunction<T, X, Y> mapper;
    private final Property<Y> value = DataBindings.getPropertyFactory().createObjectProperty();

    public AbstractBiMappedObservableValue(ObservableValue<T> a, ObservableValue<X> b, BiFunction<T, X, Y> mapper) {
        this.a = a;
        this.b = b;
        this.mapper = mapper;
        update();
        aListener = a.addListener(this::update);
        bListener = b.addListener(this::update);
    }

    @Override
    public Y get() {
        return value.get();
    }

    @Override
    public void addListener(ObservableValueChangeListener<? super Y> listener) {
        value.addListener(listener);
    }

    @Override
    public void removeListener(ObservableValueChangeListener<? super Y> listener) {
        value.removeListener(listener);
    }

    @Override
    public void onValueChange(T oldVal, T newVal) {
        update();
    }

    private void update() {
        value.set(mapper.apply(a.get(), b.get()));
    }

    @Override
    protected void finalize() {
        a.removeListener(aListener);
        b.removeListener(bListener);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
