package com.github.franckyi.guapi.common.data;

import com.github.franckyi.guapi.common.data.event.PropertyChangeEvent;

import java.util.function.Function;

class BindMappedObservableValue<T, X> implements ObservableValue<X>, PropertyChangeEvent.Listener<T> {
    private final ObservableValue<T> source;
    private final Function<T, ObservableValue<X>> mapper;
    private final boolean nullSafe;
    private final ObservableValue<X> nullValue;
    private final ObjectProperty<X> property = new SimpleObjectProperty<>();

    public BindMappedObservableValue(ObservableValue<T> source, Function<T, ObservableValue<X>> mapper) {
        this(source, mapper, false, null);
    }

    public BindMappedObservableValue(ObservableValue<T> source, Function<T, ObservableValue<X>> mapper, X nullValue) {
        this(source, mapper, true, nullValue);
    }

    private BindMappedObservableValue(ObservableValue<T> source, Function<T, ObservableValue<X>> mapper, boolean nullSafe, X nullValue) {
        this.source = source;
        this.mapper = mapper;
        this.nullSafe = nullSafe;
        this.nullValue = ObservableValue.of(nullValue);
        bind(source.getValue());
        source.addListener(this);
    }

    @Override
    public X getValue() {
        return property.getValue();
    }

    @Override
    public void addListener(PropertyChangeEvent.Listener<? super X> listener) {
        property.addListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeEvent.Listener<? super X> listener) {
        property.removeListener(listener);
    }

    @Override
    public void onChange(PropertyChangeEvent<? extends T> event) {
        bind(event.getNewValue());
    }

    private void bind(T value) {
        property.unbind();
        property.bind((nullSafe && (value == null)) ? nullValue : mapper.apply(value));
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
