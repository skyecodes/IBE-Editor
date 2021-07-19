package com.github.franckyi.databindings.impl;

import com.github.franckyi.databindings.api.Property;
import com.github.franckyi.databindings.api.event.ObservableValueChangeListener;
import com.github.franckyi.databindings.api.ObservableValue;

public abstract class AbstractReadOnlyProperty<T> implements ObservableValue<T> {
    protected final Property<T> property;

    protected AbstractReadOnlyProperty(Property<T> property) {
        this.property = property;
    }

    @Override
    public T get() {
        return property.get();
    }

    @Override
    public void addListener(ObservableValueChangeListener<? super T> listener) {
        property.addListener(listener);
    }

    @Override
    public void removeListener(ObservableValueChangeListener<? super T> listener) {
        property.removeListener(listener);
    }

    @Override
    public String toString() {
        return property.toString();
    }
}
