package com.github.franckyi.guapi.common.data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ObjectProperty<T> implements Property<T> {
    private T value;
    private final Set<ChangeListener<? super T>> listeners;
    private final ChangeListener<T> valueListener = (oldVal, newVal) -> setValue(newVal);
    private ObservableValue<? extends T> boundValue;

    public ObjectProperty() {
        this(null);
    }

    public ObjectProperty(T value) {
        this.value = value;
        listeners = new HashSet<>();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        if (!Objects.equals(this.value, value)) {
            listeners.forEach(listener -> listener.onChange(this.value, value));
            this.value = value;
        }
    }

    public void addListener(ChangeListener<? super T> listener) {
        listeners.add(listener);
    }

    public void removeListener(ChangeListener<? super T> listener) {
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
