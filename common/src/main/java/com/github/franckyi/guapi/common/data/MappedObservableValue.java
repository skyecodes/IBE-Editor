package com.github.franckyi.guapi.common.data;

import com.github.franckyi.guapi.common.data.event.PropertyChangeEvent;

import java.util.function.Function;

class MappedObservableValue<T, X> implements ObservableValue<X>, PropertyChangeEvent.Listener<T> {
    private final ObservableValue<T> source;
    private final Function<T, X> mapper;
    private final boolean nullSafe;
    private final X nullValue;
    private final ObjectProperty<X> property = new SimpleObjectProperty<>();

    public MappedObservableValue(ObservableValue<T> source, Function<T, X> mapper) {
        this(source, mapper, false, null);
    }

    public MappedObservableValue(ObservableValue<T> source, Function<T, X> mapper, X nullValue) {
        this(source, mapper, true, nullValue);
    }

    private MappedObservableValue(ObservableValue<T> source, Function<T, X> mapper, boolean nullSafe, X nullValue) {
        this.source = source;
        this.mapper = mapper;
        this.nullSafe = nullSafe;
        this.nullValue = nullValue;
        apply(source.getValue());
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
        apply(event.getNewValue());
    }

    private void apply(T value) {
        property.setValue((nullSafe && (value == null)) ? nullValue : mapper.apply(value));
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
