package com.github.franckyi.ibeeditor.api.client.mvc.base.model;

import com.github.franckyi.databindings.api.BooleanProperty;

public interface EditorEntryModel {
    default boolean isValid() {
        return validProperty().getValue();
    }

    BooleanProperty validProperty();

    default void setValid(boolean value) {
        validProperty().setValue(value);
    }

    EditorCategoryModel getCategory();

    void apply();

    Type getType();

    enum Type {
        STRING, INTEGER, TEXT, ENUM, ACTION
    }
}
