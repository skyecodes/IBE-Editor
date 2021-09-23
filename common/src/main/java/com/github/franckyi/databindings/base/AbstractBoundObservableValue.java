package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableValue;
import com.github.franckyi.databindings.api.Property;
import com.github.franckyi.databindings.api.event.ObservableValueChangeListener;

import java.util.function.Function;

public abstract class AbstractBoundObservableValue<T, X> implements ObservableValue<X>, ObservableValueChangeListener<T> {
    protected final ObservableValue<T> source;
    protected final Function<T, ObservableValue<X>> mapper;
    protected final boolean nullSafe;
    protected final ObservableValue<X> orIfNull;
    protected final Property<X> value = ObjectProperty.create();

    protected AbstractBoundObservableValue(ObservableValue<T> source, Function<T, ObservableValue<X>> mapper, boolean nullSafe, X orIfNull) {
        this.source = source;
        this.mapper = mapper;
        this.nullSafe = nullSafe;
        this.orIfNull = ObservableValue.unmodifiable(orIfNull);
        bind(source.get());
        source.addListener(this);
    }

    @Override
    public X get() {
        return value.get();
    }

    @Override
    public void addListener(ObservableValueChangeListener<? super X> listener) {
        value.addListener(listener);
    }

    @Override
    public void removeListener(ObservableValueChangeListener<? super X> listener) {
        value.removeListener(listener);
    }

    @Override
    public void onValueChange(T oldVal, T newVal) {
        bind(newVal);
    }

    private void bind(T value) {
        this.value.unbind();
        this.value.bind((nullSafe && (value == null)) ? orIfNull : mapper.apply(value));
    }

    @Override
    protected void finalize() {
        source.removeListener(this);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
