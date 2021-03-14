package com.github.franckyi.databindings.api;

/**
 * A {@link Property} that holds an integer value.
 */
public interface IntegerProperty extends Property<Integer>, ObservableIntegerValue {
    /**
     * Sets the value of the property (safer than {@link #set} since it does not allow {@code null} values).
     * @param value The new value
     */
    default void setValue(int value) {
        set(value);
    }
}
