package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.ObservableValue;
import com.github.franckyi.databindings.api.Property;
import com.github.franckyi.databindings.event.PropertyChangeEvent;
import com.github.franckyi.databindings.factory.PropertyFactory;

import java.util.function.BiFunction;

public abstract class AbstractBiMappedObservableValue<T, X> implements ObservableValue<X>, PropertyChangeEvent.Listener<T> {
    private final ObservableValue<T> a, b;
    private final BiFunction<T, T, X> function;
    private final Property<X> res = PropertyFactory.ofObject();

    public AbstractBiMappedObservableValue(ObservableValue<T> a, ObservableValue<T> b, BiFunction<T, T, X> function) {
        this.a = a;
        this.b = b;
        this.function = function;
        update();
        a.addListener(this);
        b.addListener(this);
    }

    @Override
    public X get() {
        return res.get();
    }

    @Override
    public void addListener(PropertyChangeEvent.Listener<? super X> listener) {
        res.addListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeEvent.Listener<? super X> listener) {
        res.removeListener(listener);
    }

    @Override
    public void onChange(PropertyChangeEvent<? extends T> event) {
        update();
    }

    private void update() {
        res.set(function.apply(a.get(), b.get()));
    }

    @Override
    protected void finalize() {
        a.removeListener(this);
        b.removeListener(this);
    }
}
