package com.github.franckyi.databindings.api;

import com.github.franckyi.databindings.DataBindings;

public interface ObservableObjectValue<T> extends ObservableValue<T> {
    static <T> ObservableObjectValue<T> readOnly(ObjectProperty<T> property) {
        return DataBindings.getPropertyFactory().createReadOnlyProperty(property);
    }

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
