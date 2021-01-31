package com.github.franckyi.guapi.common.data;

import com.github.franckyi.guapi.common.data.event.PropertyChangeEvent;

public class ReadOnlyProperty<T> implements ObservableValue<T> {
    private final Property<T> property;

    public ReadOnlyProperty(Property<T> property) {
        this.property = property;
    }

    @Override
    public T getValue() {
        return property.getValue();
    }

    @Override
    public void addListener(PropertyChangeEvent.Listener<? super T> listener) {
        property.addListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeEvent.Listener<? super T> listener) {
        property.removeListener(listener);
    }
}
