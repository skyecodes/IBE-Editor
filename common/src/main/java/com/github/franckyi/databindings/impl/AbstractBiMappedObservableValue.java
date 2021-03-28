package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.ObservableValue;
import com.github.franckyi.databindings.api.Property;
import com.github.franckyi.databindings.api.event.ObservableValueChangeListener;

import java.util.function.BiFunction;

public abstract class AbstractBiMappedObservableValue<T, X> implements ObservableValue<X>, ObservableValueChangeListener<T> {
    private final ObservableValue<T> a, b;
    private final BiFunction<T, T, X> mapper;
    private final Property<X> res = Bindings.getPropertyFactory().ofObject();

    public AbstractBiMappedObservableValue(ObservableValue<T> a, ObservableValue<T> b, BiFunction<T, T, X> mapper) {
        this.a = a;
        this.b = b;
        this.mapper = mapper;
        update();
        a.addListener(this);
        b.addListener(this);
    }

    @Override
    public X get() {
        return res.get();
    }

    @Override
    public void addListener(ObservableValueChangeListener<? super X> listener) {
        res.addListener(listener);
    }

    @Override
    public void removeListener(ObservableValueChangeListener<? super X> listener) {
        res.removeListener(listener);
    }

    @Override
    public void onValueChange(T oldVal, T newVal) {
        update();
    }

    private void update() {
        res.set(mapper.apply(a.get(), b.get()));
    }

    @Override
    protected void finalize() {
        a.removeListener(this);
        b.removeListener(this);
    }

    @Override
    public String toString() {
        return res.toString();
    }
}
