package com.github.franckyi.databindings.api.event;

@FunctionalInterface
public interface PropertyChangeListener<T> {
    void onChange(T oldVal, T newVal);
}
