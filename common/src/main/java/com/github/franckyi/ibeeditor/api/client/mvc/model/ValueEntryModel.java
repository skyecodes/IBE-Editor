package com.github.franckyi.ibeeditor.api.client.mvc.model;

import com.github.franckyi.databindings.api.Property;

public interface ValueEntryModel<T> extends EntryModel {
    T getDefaultValue();

    default T getValue() {
        return valueProperty().get();
    }

    Property<T> valueProperty();

    default void setValue(T value) {
        valueProperty().set(value);
    }
}
