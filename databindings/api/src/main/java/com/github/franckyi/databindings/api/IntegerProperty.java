package com.github.franckyi.databindings.api;

import com.github.franckyi.databindings.DataBindings;

/**
 * A {@link Property} that holds an integer value.
 */
public interface IntegerProperty extends Property<Integer>, ObservableIntegerValue {
    static IntegerProperty create() {
        return DataBindings.getPropertyFactory().createIntegerProperty();
    }

    static IntegerProperty create(int value) {
        return DataBindings.getPropertyFactory().createIntegerProperty(value);
    }

    /**
     * Sets the value of the property (safer than {@link #set} since it does not allow {@code null} values).
     *
     * @param value The new value
     */
    default void setValue(int value) {
        set(value);
    }

    default void incr() {
        incr(1);
    }

    default void incr(int value) {
        setValue(getValue() + value);
    }

    default void decr() {
        decr(1);
    }

    default void decr(int value) {
        setValue(getValue() - value);
    }
}
