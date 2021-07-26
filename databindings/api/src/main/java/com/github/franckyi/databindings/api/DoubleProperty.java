package com.github.franckyi.databindings.api;

/**
 * A {@link Property} that holds an double value.
 */
public interface DoubleProperty extends Property<Double>, ObservableDoubleValue {
    /**
     * Sets the value of the property (safer than {@link #set} since it does not allow {@code null} values).
     *
     * @param value The new value
     */
    default void setValue(double value) {
        set(value);
    }

    default void incr() {
        incr(1);
    }

    default void incr(double value) {
        setValue(getValue() + value);
    }

    default void decr() {
        decr(1);
    }

    default void decr(double value) {
        setValue(getValue() - value);
    }
}
