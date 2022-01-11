package com.github.franckyi.databindings.base;

import com.github.franckyi.databindings.api.ObservableValue;
import com.github.franckyi.databindings.api.Property;
import com.github.franckyi.databindings.api.event.ObservableValueChangeListener;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractProperty<T> implements Property<T> {
    protected final List<ObservableValueChangeListener<? super T>> listeners = new CopyOnWriteArrayList<>();
    protected T value;
    protected final ObservableValueChangeListener<T> valueListener = (oldVal, newVal) -> doSet(newVal);
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
        if (isBound() && !(boundValue instanceof Property<?> property && property.isBound())) {
            throw new IllegalStateException("Property is bound to a value and cannot be set");
        }
        doSet(value);
    }

    private void doSet(T value) {
        if (!Objects.equals(this.value, value)) {
            T old = this.value;
            this.value = value;
            listeners.forEach(listener -> listener.onValueChange(old, value));
        }
    }

    @Override
    public void addListener(ObservableValueChangeListener<? super T> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ObservableValueChangeListener<? super T> listener) {
        listeners.remove(listener);
    }

    @Override
    public boolean isBound() {
        return boundValue != null;
    }

    @Override
    public void bind(ObservableValue<? extends T> value) {
        unbind();
        set(value.get());
        value.addListener(valueListener);
        boundValue = value;
    }

    @Override
    public void unbind() {
        if (isBound()) {
            boundValue.removeListener(valueListener);
            boundValue = null;
        }
    }

    @Override
    public void bindBidirectional(Property<T> other) {
        bind(other);
        other.bind(this);
    }

    @Override
    public void unbindBidirectional() {
        unbind();
        if (boundValue instanceof Property<?> property) {
            property.unbind();
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
