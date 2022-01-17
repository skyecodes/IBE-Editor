package com.github.franckyi.databindings.api;

import java.util.function.IntSupplier;

/**
 * An {@link ObservableValue} that holds an integer value.
 */
public interface ObservableIntegerValue extends ObservableValue<Integer> {
    /**
     * Creates an unmodifiable (constant) {@link ObservableIntegerValue}.
     *
     * @param value The constant value of the {@link ObservableIntegerValue}
     * @return The unmodifiable {@link ObservableIntegerValue}
     */
    static ObservableIntegerValue unmodifiable(int value) {
        return new Unmodifiable() {
            @Override
            public Integer get() {
                return value;
            }
        };
    }

    abstract class Unmodifiable extends ObservableValue.Unmodifiable<Integer> implements ObservableIntegerValue {
    }

    static ObservableIntegerValue readOnly(IntegerProperty property) {
        return DataBindings.getPropertyFactory().createReadOnlyProperty(property);
    }

    static ObservableIntegerValue observe(IntSupplier supplier, ObservableValue<?>... triggers) {
        return DataBindings.getMappingFactory().createIntegerMapping(supplier, triggers);
    }

    /**
     * Gets the value (safer than {@link #get}, will return {@code 0} is the value is {@code null}).
     *
     * @return The value
     */
    default int getValue() {
        return get() == null ? 0 : get();
    }

    /**
     * Creates a new {@link ObservableIntegerValue} that is always equal to the addition of this value
     * and another integer.
     *
     * @param other The other integer to add
     * @return The new {@link ObservableIntegerValue}
     */
    default ObservableIntegerValue add(int other) {
        return mapToInt(i -> i + other);
    }

    /**
     * Creates a new {@link ObservableIntegerValue} that is always equal to the substraction of this value
     * by another integer.
     *
     * @param other The other integer to substract
     * @return The new {@link ObservableIntegerValue}
     */
    default ObservableIntegerValue substract(int other) {
        return mapToInt(i -> i - other);
    }

    /**
     * Creates a new {@link ObservableIntegerValue} that is always equal to the multiplication of this value
     * and another integer.
     *
     * @param other The other integer to multiply
     * @return The new {@link ObservableIntegerValue}
     */
    default ObservableIntegerValue multiply(int other) {
        return mapToInt(i -> i * other);
    }

    /**
     * Creates a new {@link ObservableIntegerValue} that is always equal to the division of this value
     * by another integer.
     *
     * @param other The other integer to divide
     * @return The new {@link ObservableIntegerValue}
     */
    default ObservableIntegerValue divide(int other) {
        return mapToInt(i -> i / other);
    }

    /**
     * Creates a new {@link ObservableIntegerValue} that is always equal to the addition of this value
     * and another observale integer value.
     *
     * @param other The other observale integer value to add
     * @return The new {@link ObservableIntegerValue}
     */
    default ObservableIntegerValue add(ObservableValue<Integer> other) {
        return observe(() -> get() + other.get(), this, other);
    }

    /**
     * Creates a new {@link ObservableIntegerValue} that is always equal to the substraction of this value
     * by another observale integer value.
     *
     * @param other The other observale integer value to substract
     * @return The new {@link ObservableIntegerValue}
     */
    default ObservableIntegerValue substract(ObservableValue<Integer> other) {
        return observe(() -> get() - other.get(), this, other);
    }

    /**
     * Creates a new {@link ObservableIntegerValue} that is always equal to the multiplication of this value
     * and another observale integer value.
     *
     * @param other The other observale integer value to multiply
     * @return The new {@link ObservableIntegerValue}
     */
    default ObservableIntegerValue multiply(ObservableValue<Integer> other) {
        return observe(() -> get() * other.get(), this, other);
    }

    /**
     * Creates a new {@link ObservableIntegerValue} that is always equal to the division of this value
     * by another observale integer value.
     *
     * @param other The other observale integer value to divide
     * @return The new {@link ObservableIntegerValue}
     */
    default ObservableIntegerValue divide(ObservableValue<Integer> other) {
        return observe(() -> get() / other.get(), this, other);
    }
}
