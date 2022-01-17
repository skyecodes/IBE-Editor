package com.github.franckyi.databindings.api;

import java.util.function.BooleanSupplier;

/**
 * An {@link ObservableValue} that holds a boolean value.
 */
public interface ObservableBooleanValue extends ObservableValue<Boolean> {
    ObservableBooleanValue TRUE = unmodifiable(true);
    ObservableBooleanValue FALSE = unmodifiable(false);

    /**
     * Creates an unmodifiable (constant) {@link ObservableBooleanValue}.
     *
     * @param value The constant value of the {@link ObservableBooleanValue}
     * @return The unmodifiable {@link ObservableBooleanValue}
     */
    static ObservableBooleanValue unmodifiable(boolean value) {
        return new ObservableBooleanValue.Unmodifiable() {
            @Override
            public Boolean get() {
                return value;
            }
        };
    }

    abstract class Unmodifiable extends ObservableValue.Unmodifiable<Boolean> implements ObservableBooleanValue {
    }

    static ObservableBooleanValue readOnly(BooleanProperty property) {
        return DataBindings.getPropertyFactory().createReadOnlyProperty(property);
    }

    static ObservableBooleanValue observe(BooleanSupplier supplier, ObservableValue<?>... triggers) {
        return DataBindings.getMappingFactory().createBooleanMapping(supplier, triggers);
    }

    /**
     * Gets the value (safer than {@link #get}, will return {@code false} is the value is {@code null}).
     *
     * @return The value
     */
    default boolean getValue() {
        return get() != null && get();
    }

    /**
     * Creates a new {@link ObservableBooleanValue} that is always equal to the opposite of this value.
     *
     * @return The new {@link ObservableBooleanValue}
     */
    default ObservableBooleanValue not() {
        return mapToBoolean(b -> !b);
    }

    /**
     * Creates a new {@link ObservableBooleanValue} that is always equal to the result of an {@code OR} operation between
     * this value and another observable boolean value.
     *
     * @param other Another observable boolean value
     * @return The new {@link ObservableBooleanValue}
     */
    default ObservableBooleanValue or(ObservableValue<Boolean> other) {
        return observe(() -> get() || other.get(), this, other);
    }

    /**
     * Creates a new {@link ObservableBooleanValue} that is always equal to the result of an {@code AND} operation between
     * this value and another observable boolean value.
     *
     * @param other Another observable boolean value
     * @return The new {@link ObservableBooleanValue}
     */
    default ObservableBooleanValue and(ObservableValue<Boolean> other) {
        return observe(() -> get() && other.get(), this, other);
    }
}
