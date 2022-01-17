package com.github.franckyi.databindings.api;

import java.util.function.DoubleSupplier;

/**
 * An {@link ObservableValue} that holds a double value.
 */
public interface ObservableDoubleValue extends ObservableValue<Double> {
    /**
     * Creates an unmodifiable (constant) {@link ObservableDoubleValue}.
     *
     * @param value The constant value of the {@link ObservableDoubleValue}
     * @return The unmodifiable {@link ObservableDoubleValue}
     */
    static ObservableDoubleValue unmodifiable(double value) {
        return new Unmodifiable() {
            @Override
            public Double get() {
                return value;
            }
        };
    }

    abstract class Unmodifiable extends ObservableValue.Unmodifiable<Double> implements ObservableDoubleValue {
    }

    static ObservableDoubleValue readOnly(DoubleProperty property) {
        return DataBindings.getPropertyFactory().createReadOnlyProperty(property);
    }

    static ObservableDoubleValue observe(DoubleSupplier supplier, ObservableValue<?>... triggers) {
        return DataBindings.getMappingFactory().createDoubleMapping(supplier, triggers);
    }

    default double getValue() {
        return get() == null ? 0 : get();
    }

    /**
     * Creates a new {@link ObservableDoubleValue} that is always equal to the addition of this value
     * and another double.
     *
     * @param other The other double to add
     * @return The new {@link ObservableDoubleValue}
     */
    default ObservableDoubleValue add(double other) {
        return mapToDouble(i -> i + other);
    }

    /**
     * Creates a new {@link ObservableDoubleValue} that is always equal to the substraction of this value
     * by another double.
     *
     * @param other The other double to substract
     * @return The new {@link ObservableDoubleValue}
     */
    default ObservableDoubleValue substract(double other) {
        return mapToDouble(i -> i - other);
    }

    /**
     * Creates a new {@link ObservableDoubleValue} that is always equal to the multiplication of this value
     * and another double.
     *
     * @param other The other double to multiply
     * @return The new {@link ObservableDoubleValue}
     */
    default ObservableDoubleValue multiply(double other) {
        return mapToDouble(i -> i * other);
    }

    /**
     * Creates a new {@link ObservableDoubleValue} that is always equal to the division of this value
     * by another double.
     *
     * @param other The other double to divide
     * @return The new {@link ObservableDoubleValue}
     */
    default ObservableDoubleValue divide(double other) {
        return mapToDouble(i -> i / other);
    }

    /**
     * Creates a new {@link ObservableDoubleValue} that is always equal to the addition of this value
     * and another observale double value.
     *
     * @param other The other observale double value to add
     * @return The new {@link ObservableDoubleValue}
     */
    default ObservableDoubleValue add(ObservableValue<Double> other) {
        return observe(() -> get() + other.get(), this, other);
    }

    /**
     * Creates a new {@link ObservableDoubleValue} that is always equal to the substraction of this value
     * by another observale double value.
     *
     * @param other The other observale double value to substract
     * @return The new {@link ObservableDoubleValue}
     */
    default ObservableDoubleValue substract(ObservableValue<Double> other) {
        return observe(() -> get() - other.get(), this, other);
    }

    /**
     * Creates a new {@link ObservableDoubleValue} that is always equal to the multiplication of this value
     * and another observale double value.
     *
     * @param other The other observale double value to multiply
     * @return The new {@link ObservableDoubleValue}
     */
    default ObservableDoubleValue multiply(ObservableValue<Double> other) {
        return observe(() -> get() * other.get(), this, other);
    }

    /**
     * Creates a new {@link ObservableDoubleValue} that is always equal to the division of this value
     * by another observale double value.
     *
     * @param other The other observale double value to divide
     * @return The new {@link ObservableDoubleValue}
     */
    default ObservableDoubleValue divide(ObservableValue<Double> other) {
        return observe(() -> get() / other.get(), this, other);
    }
}
