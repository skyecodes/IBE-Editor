package com.github.franckyi.databindings.api;

/**
 * An {@link ObservableValue} that holds a double value.
 */
public interface ObservableDoubleValue extends ObservableValue<Double> {
    static ObservableDoubleValue readOnly(DoubleProperty property) {
        return DataBindings.getPropertyFactory().createReadOnlyProperty(property);
    }

    default double getValue() {
        return get() == null ? 0 : get();
    }

    /**
     * Creates a new {@link ObservableDoubleValue} that is always equal to the addition of this value
     * and an other double.
     *
     * @param other The other double to add
     * @return The new {@link ObservableDoubleValue}
     */
    default ObservableDoubleValue add(double other) {
        return mapToDouble(i -> i + other);
    }

    /**
     * Creates a new {@link ObservableDoubleValue} that is always equal to the substraction of this value
     * by an other double.
     *
     * @param other The other double to substract
     * @return The new {@link ObservableDoubleValue}
     */
    default ObservableDoubleValue substract(double other) {
        return mapToDouble(i -> i - other);
    }

    /**
     * Creates a new {@link ObservableDoubleValue} that is always equal to the multiplication of this value
     * and an other double.
     *
     * @param other The other double to multiply
     * @return The new {@link ObservableDoubleValue}
     */
    default ObservableDoubleValue multiply(double other) {
        return mapToDouble(i -> i * other);
    }

    /**
     * Creates a new {@link ObservableDoubleValue} that is always equal to the division of this value
     * by an other double.
     *
     * @param other The other double to divide
     * @return The new {@link ObservableDoubleValue}
     */
    default ObservableDoubleValue divide(double other) {
        return mapToDouble(i -> i / other);
    }

    /**
     * Creates a new {@link ObservableDoubleValue} that is always equal to the addition of this value
     * and an other observale double value.
     *
     * @param other The other observale double value to add
     * @return The new {@link ObservableDoubleValue}
     */
    default ObservableDoubleValue add(ObservableValue<Double> other) {
        return mapToDouble(other, Double::sum);
    }

    /**
     * Creates a new {@link ObservableDoubleValue} that is always equal to the substraction of this value
     * by an other observale double value.
     *
     * @param other The other observale double value to substract
     * @return The new {@link ObservableDoubleValue}
     */
    default ObservableDoubleValue substract(ObservableValue<Double> other) {
        return mapToDouble(other, (x, y) -> x - y);
    }

    /**
     * Creates a new {@link ObservableDoubleValue} that is always equal to the multiplication of this value
     * and an other observale double value.
     *
     * @param other The other observale double value to multiply
     * @return The new {@link ObservableDoubleValue}
     */
    default ObservableDoubleValue multiply(ObservableValue<Double> other) {
        return mapToDouble(other, (x, y) -> x * y);
    }

    /**
     * Creates a new {@link ObservableDoubleValue} that is always equal to the division of this value
     * by an other observale double value.
     *
     * @param other The other observale double value to divide
     * @return The new {@link ObservableDoubleValue}
     */
    default ObservableDoubleValue divide(ObservableValue<Double> other) {
        return mapToDouble(other, (x, y) -> x / y);
    }
}
