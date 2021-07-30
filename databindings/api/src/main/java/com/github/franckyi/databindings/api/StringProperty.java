package com.github.franckyi.databindings.api;

import com.github.franckyi.databindings.DataBindings;

/**
 * A {@link Property} that holds a {@link String} value.
 */
public interface StringProperty extends ObjectProperty<String>, ObservableStringValue {
    static StringProperty create() {
        return DataBindings.getPropertyFactory().createStringProperty();
    }

    static StringProperty create(String value) {
        return DataBindings.getPropertyFactory().createStringProperty(value);
    }
}
