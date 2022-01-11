package com.github.franckyi.databindings.api;

/**
 * An {@link ObservableValue} that holds a boolean value.
 */
public interface ObservableBooleanValue extends ObservableValue<Boolean> {
    static ObservableBooleanValue readOnly(BooleanProperty property) {
        return DataBindings.getPropertyFactory().createReadOnlyProperty(property);
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
        return mapToBoolean(other, (x, y) -> x || y);
    }

    /**
     * Creates a new {@link ObservableBooleanValue} that is always equal to the result of an {@code AND} operation between
     * this value and another observable boolean value.
     *
     * @param value Another observable boolean value
     * @return The new {@link ObservableBooleanValue}
     */
    default ObservableBooleanValue and(ObservableValue<Boolean> value) {
        return mapToBoolean(value, (x, y) -> x && y);
    }
}
