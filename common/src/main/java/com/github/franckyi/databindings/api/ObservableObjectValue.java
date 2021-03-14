package com.github.franckyi.databindings.api;

public interface ObservableObjectValue<T> extends ObservableValue<T> {
    /**
     * Gets the value (no difference with {@link #get}, but is preferable to use)
     * @return The value
     */
    default T getValue() {
        return get();
    }

    /**
     * @return Whether or not this value is null
     */
    default boolean hasValue() {
        return get() != null;
    }
}
