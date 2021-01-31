package com.github.franckyi.guapi.common.data.event;

public class PropertyChangeEvent<T> {
    private final T oldValue, newValue;

    public PropertyChangeEvent(T oldValue, T newValue) {
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public T getOldValue() {
        return oldValue;
    }

    public T getNewValue() {
        return newValue;
    }

    public interface Listener<T> {
        void onChange(PropertyChangeEvent<? extends T> event);
    }
}
