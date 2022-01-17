package com.github.franckyi.databindings.api;

import java.util.function.Supplier;

public interface ObservableStringValue extends ObservableObjectValue<String> {
    /**
     * Creates an unmodifiable (constant) {@link ObservableStringValue}.
     *
     * @param value The constant value of the {@link ObservableStringValue}
     * @return The unmodifiable {@link ObservableStringValue}
     */
    static ObservableStringValue unmodifiable(String value) {
        return new Unmodifiable() {
            @Override
            public String get() {
                return value;
            }
        };
    }

    abstract class Unmodifiable extends ObservableValue.Unmodifiable<String> implements ObservableStringValue {
    }

    static ObservableStringValue readOnly(StringProperty property) {
        return DataBindings.getPropertyFactory().createReadOnlyProperty(property);
    }

    static ObservableStringValue observe(Supplier<String> supplier, ObservableValue<?>... triggers) {
        return DataBindings.getMappingFactory().createStringMapping(supplier, triggers);
    }

    /**
     * Creates a new {@link ObservableStringValue} that is always equal to the concatenation of this value
     * and another {@link String}.
     *
     * @param other The string to append
     * @return The new {@link ObservableStringValue}
     */
    default ObservableStringValue append(String other) {
        return mapToString(s -> s + other, other);
    }

    /**
     * Creates a new {@link ObservableStringValue} that is always equal to the concatenation of another {@link String}
     * and this value.
     *
     * @param other The string to append
     * @return The new {@link ObservableStringValue}
     */
    default ObservableStringValue prepend(String other) {
        return mapToString(s -> other + s, other);
    }

    /**
     * Creates a new {@link ObservableStringValue} that is always equal to the concatenation of this value
     * and another observable string value.
     *
     * @param other The observable string value to append
     * @return The new {@link ObservableStringValue}
     */
    default ObservableStringValue append(ObservableValue<String> other) {
        return observe(() -> get() + other.get(), this, other);
    }

    /**
     * Creates a new {@link ObservableStringValue} that is always equal to the concatenation of this value
     * and another observable string value.
     *
     * @param other The observable string value to append
     * @return The new {@link ObservableStringValue}
     */
    default ObservableStringValue prepend(ObservableValue<String> other) {
        return observe(() -> other.get() + get(), this, other);
    }
}
