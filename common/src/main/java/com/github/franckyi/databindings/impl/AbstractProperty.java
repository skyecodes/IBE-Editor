package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.ObservableValue;
import com.github.franckyi.databindings.api.Property;
import com.github.franckyi.databindings.event.PropertyChangeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractProperty<T> implements Property<T> {
    protected final List<PropertyChangeEvent.Listener<? super T>> listeners = new ArrayList<>();
    protected T value;
    protected final PropertyChangeEvent.Listener<T> valueListener = event -> doSet(event.getNewValue());
    protected ObservableValue<? extends T> boundValue;

    protected AbstractProperty() {
        this(null);
    }

    protected AbstractProperty(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(T value) {
        if (isBound() && !(boundValue instanceof Property<?> && ((Property<?>) boundValue).isBound())) {
            throw new IllegalStateException("Property is bound to a value and cannot be set");
        }
        doSet(value);
    }

    protected void doSet(T value) {
        if (!Objects.equals(this.value, value)) {
            PropertyChangeEvent<T> event = new PropertyChangeEvent<>(this.value, value);
            this.value = value;
            for (int i = 0; i < listeners.size(); i++) { // avoid ConcurrentModificationException
                listeners.get(i).onChange(event);
            }
        }
    }

    @Override
    public void addListener(PropertyChangeEvent.Listener<? super T> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(PropertyChangeEvent.Listener<? super T> listener) {
        listeners.remove(listener);
    }

    @Override
    public boolean isBound() {
        return boundValue != null;
    }

    @Override
    public void bind(ObservableValue<? extends T> o) {
        if (!isBound()) {
            set(o.get());
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

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
