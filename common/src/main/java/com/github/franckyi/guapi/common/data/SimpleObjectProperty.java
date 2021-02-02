package com.github.franckyi.guapi.common.data;

import com.github.franckyi.guapi.common.data.event.PropertyChangeEvent;

import java.util.*;

public class SimpleObjectProperty<T> implements ObjectProperty<T> {
    private final List<PropertyChangeEvent.Listener<? super T>> listeners;
    private T value;
    private final PropertyChangeEvent.Listener<T> valueListener = event -> setValue(event.getNewValue());
    private ObservableValue<? extends T> boundValue;

    public SimpleObjectProperty() {
        this(null);
    }

    public SimpleObjectProperty(T value) {
        this.value = value;
        listeners = new ArrayList<>();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        if (!Objects.equals(this.value, value)) {
            PropertyChangeEvent<T> event = new PropertyChangeEvent<>(this.value, value);
            this.value = value;
            listeners.forEach(listener -> listener.onChange(event));
        }
    }

    public void addListener(PropertyChangeEvent.Listener<? super T> listener) {
        listeners.add(listener);
    }

    public void removeListener(PropertyChangeEvent.Listener<? super T> listener) {
        listeners.remove(listener);
    }

    public boolean isBound() {
        return boundValue != null;
    }

    @Override
    public void bind(ObservableValue<? extends T> o) {
        if (!isBound()) {
            o.addListener(valueListener);
            this.boundValue = o;
            setValue(o.getValue());
        }
    }

    @Override
    public void unbind() {
        if (isBound()) {
            boundValue.removeListener(valueListener);
            boundValue = null;
        }
    }

    @Override
    public void bindBidirectional(ObjectProperty<T> property) {
        if (!isBound() && !property.isBound()) {
            this.bind(property);
            property.bind(this);
        }
    }

    @Override
    public void unbindBidirectional(ObjectProperty<T> property) {
        if (isBound() && property.isBound() && boundValue == property) {
            this.unbind();
            property.unbind();
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
