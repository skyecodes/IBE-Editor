package com.github.franckyi.ibeeditor.api.client.mvc.editor.model;

import com.github.franckyi.databindings.api.BooleanProperty;

public interface EntryModel {
    default boolean isValid() {
        return validProperty().getValue();
    }

    BooleanProperty validProperty();

    default void setValid(boolean value) {
        validProperty().setValue(value);
    }

    CategoryModel getCategory();

    void apply();

    EntryType getType();
}
