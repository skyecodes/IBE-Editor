package com.github.franckyi.ibeeditor.api.client.mvc.base.model;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.guapi.api.mvc.Model;

public interface EditorEntryModel extends Model {
    EditorCategoryModel getCategory();

    default boolean isValid() {
        return validProperty().getValue();
    }

    BooleanProperty validProperty();

    default void setValid(boolean value) {
        validProperty().setValue(value);
    }

    default int getListIndex() {
        return listIndexProperty().getValue();
    }

    IntegerProperty listIndexProperty();

    default void setListIndex(int value) {
        listIndexProperty().setValue(value);
    }

    default boolean isResetable() {
        return true;
    }

    default int getListSize() {
        return listSizeProperty().getValue();
    }

    IntegerProperty listSizeProperty();

    default void setListSize(int value) {
        listSizeProperty().setValue(value);
    }

    void apply();

    Type getType();

    enum Type {
        STRING, INTEGER, TEXT, ENUM, ACTION, ADD_LIST_ENTRY, BOOLEAN
    }
}
