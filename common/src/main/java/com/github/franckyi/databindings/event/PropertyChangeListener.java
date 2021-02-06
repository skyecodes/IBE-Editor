package com.github.franckyi.databindings.event;

public interface PropertyChangeListener<T> {
    void onChange(T oldVal, T newVal);
}
