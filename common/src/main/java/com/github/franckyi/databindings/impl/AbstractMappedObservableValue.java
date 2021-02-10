package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.ObservableValue;
import com.github.franckyi.databindings.api.Property;
import com.github.franckyi.databindings.api.event.PropertyChangeListener;

import java.util.function.Function;

public abstract class AbstractMappedObservableValue<T, X> implements ObservableValue<X>, PropertyChangeListener<T> {
    protected final ObservableValue<T> source;
    protected final Function<T, X> mapper;
    protected final boolean nullSafe;
    protected final X orIfNull;
    protected final Property<X> property = PropertyFactory.ofObject();

    protected AbstractMappedObservableValue(ObservableValue<T> source, Function<T, X> mapper, boolean nullSafe, X orIfNull) {
        this.source = source;
        this.mapper = mapper;
        this.nullSafe = nullSafe;
        this.orIfNull = orIfNull;
        apply(source.get());
        source.addListener(this);
    }

    @Override
    public X get() {
        return property.get();
    }

    @Override
    public void addListener(PropertyChangeListener<? super X> listener) {
        property.addListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener<? super X> listener) {
        property.removeListener(listener);
    }

    @Override
    public void onChange(T oldVal, T newVal) {
        apply(newVal);
    }

    private void apply(T value) {
        property.set((nullSafe && (value == null)) ? orIfNull : mapper.apply(value));
    }

    @Override
    protected void finalize() {
        source.removeListener(this);
    }

    @Override
    public String toString() {
        return property.toString();
    }

}
