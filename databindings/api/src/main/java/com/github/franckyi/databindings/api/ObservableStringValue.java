package com.github.franckyi.databindings.api;

import com.github.franckyi.databindings.DataBindings;

public interface ObservableStringValue extends ObservableObjectValue<String> {
    static ObservableStringValue readOnly(StringProperty property) {
        return DataBindings.getPropertyFactory().createReadOnlyProperty(property);
    }

    /**
     * Creates a new {@link ObservableStringValue} that is always equal to the concatenation of this value
     * and another {@link String}.
     * @param other The string to append
     * @return The new {@link ObservableStringValue}
     */
    default ObservableStringValue append(String other) {
        return mapToString(s -> s + other, other);
    }

    /**
     * Creates a new {@link ObservableStringValue} that is always equal to the concatenation of this value
     * and another observable string value.
     * @param other The observable string value to append
     * @return The new {@link ObservableStringValue}
     */
    default ObservableStringValue append(ObservableValue<String> other) {
        return mapToString(other, (s0, s1) -> s0 + s1);
    }
}
