package com.github.franckyi.databindings.api;

import java.util.function.Supplier;

public interface ObservableObjectValue<T> extends ObservableValue<T> {
    /**
     * Creates an unmodifiable (constant) {@link ObservableObjectValue}.
     *
     * @param value The constant value of the {@link ObservableObjectValue}
     * @param <T>   The type of the value that is observed
     * @return The unmodifiable {@link ObservableObjectValue}
     */
    static <T> ObservableObjectValue<T> unmodifiable(T value) {
        return new Unmodifiable<>() {
            @Override
            public T get() {
                return value;
            }
        };
    }

    abstract class Unmodifiable<T> extends ObservableValue.Unmodifiable<T> implements ObservableObjectValue<T> {
    }

    static <T> ObservableObjectValue<T> readOnly(ObjectProperty<T> property) {
        return DataBindings.getPropertyFactory().createReadOnlyProperty(property);
    }

    static <T> ObservableObjectValue<T> observe(Supplier<T> valueSupplier, ObservableValue<?>... triggers) {
        return DataBindings.getMappingFactory().createMapping(valueSupplier, triggers);
    }

    /**
     * Gets the value (no difference with {@link #get}, but is preferable to use)
     *
     * @return The value
     */
    default T getValue() {
        return get();
    }

    /**
     * @return Whether this value is null
     */
    default boolean hasValue() {
        return get() != null;
    }
}
