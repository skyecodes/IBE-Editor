package com.github.franckyi.databindings.api;

/**
 * A {@link Property} that holds an {@link Object} value.
 */
public interface ObjectProperty<T> extends Property<T>, ObservableObjectValue<T> {
    /**
     * Sets the value of the property (no difference with {@link #set}, but is preferable to use)
     * @param value The new value
     */
    default void setValue(T value) {
        set(value);
    }
}
