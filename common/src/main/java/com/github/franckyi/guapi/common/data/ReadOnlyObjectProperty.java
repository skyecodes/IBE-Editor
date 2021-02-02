package com.github.franckyi.guapi.common.data;

import com.github.franckyi.guapi.common.data.event.PropertyChangeEvent;

public class ReadOnlyObjectProperty<T> implements ObservableValue<T> {
    private final ObjectProperty<T> property;

    public ReadOnlyObjectProperty(ObjectProperty<T> property) {
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

    @Override
    public String toString() {
        return property.toString();
    }
}
