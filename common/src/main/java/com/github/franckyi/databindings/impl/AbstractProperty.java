package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.ObservableValue;
import com.github.franckyi.databindings.api.Property;
import com.github.franckyi.databindings.api.event.PropertyChangeListener;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractProperty<T> implements Property<T> {
    protected final List<PropertyChangeListener<? super T>> listeners = new CopyOnWriteArrayList<>();
    protected T value;
    protected final PropertyChangeListener<T> valueListener = (oldVal, newVal) -> doSet(newVal);
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
            T old = this.value;
            this.value = value;
            listeners.forEach(listener -> listener.onChange(old, value));
        }
    }

    @Override
    public void addListener(PropertyChangeListener<? super T> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener<? super T> listener) {
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
