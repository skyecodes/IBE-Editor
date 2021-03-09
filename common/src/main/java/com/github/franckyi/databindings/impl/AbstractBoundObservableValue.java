package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.ObservableValue;
import com.github.franckyi.databindings.api.Property;
import com.github.franckyi.databindings.api.event.PropertyChangeListener;

import java.util.function.Function;

public abstract class AbstractBoundObservableValue<T, X> implements ObservableValue<X>, PropertyChangeListener<T> {
    protected final ObservableValue<T> source;
    protected final Function<T, ObservableValue<X>> mapper;
    protected final boolean nullSafe;
    protected final ObservableValue<X> orIfNull;
    protected final Property<X> property = Bindings.getPropertyFactory().ofObject();

    protected AbstractBoundObservableValue(ObservableValue<T> source, Function<T, ObservableValue<X>> mapper, boolean nullSafe, X orIfNull) {
        this.source = source;
        this.mapper = mapper;
        this.nullSafe = nullSafe;
        this.orIfNull = ObservableValue.of(orIfNull);
        bind(source.get());
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
        bind(newVal);
    }

    private void bind(T value) {
        property.unbind();
        property.bind((nullSafe && (value == null)) ? orIfNull : mapper.apply(value));
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
