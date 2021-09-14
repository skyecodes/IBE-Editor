package com.github.franckyi.databindings.api.event;

import com.github.franckyi.databindings.api.ObservableValue;

/**
 * A listener that can be added to an {@link ObservableValue}using the
 * {@link ObservableValue#addListener} method.
 *
 * @param <T> The type of the value that is observed
 */
@FunctionalInterface
public interface ObservableValueChangeListener<T> {
    /**
     * Called whenever the value is changed.
     *
     * @param oldVal The old value (before the change)
     * @param newVal The new value (after the change)
     */
    void onValueChange(T oldVal, T newVal);
}
