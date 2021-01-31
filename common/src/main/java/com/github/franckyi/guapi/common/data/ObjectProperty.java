package com.github.franckyi.guapi.common.data;

import com.github.franckyi.guapi.common.data.event.PropertyChangeEvent;

import java.util.*;

public class ObjectProperty<T> implements Property<T> {
    private T value;
    private final List<PropertyChangeEvent.Listener<? super T>> listeners;
    private final PropertyChangeEvent.Listener<T> valueListener = event -> setValue(event.getNewValue());
    private ObservableValue<? extends T> boundValue;

    public ObjectProperty() {
        this(null);
    }

    public ObjectProperty(T value) {
        this.value = value;
        listeners = new ArrayList<>();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        if (!Objects.equals(this.value, value)) {
            PropertyChangeEvent<T> event = new PropertyChangeEvent<>(this.value, value);
            listeners.forEach(listener -> listener.onChange(event));
            this.value = value;
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
    public void bindBidirectional(Property<T> property) {
        if (!isBound() && !property.isBound()) {
            this.bind(property);
            property.bind(this);
        }
    }

    @Override
    public void unbindBidirectional(Property<T> property) {
        if (isBound() && property.isBound() && boundValue == property) {
            this.unbind();
            property.unbind();
        }
    }
}
