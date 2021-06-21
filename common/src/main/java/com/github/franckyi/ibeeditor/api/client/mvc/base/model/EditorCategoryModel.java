package com.github.franckyi.ibeeditor.api.client.mvc.base.model;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.api.StringProperty;

public interface EditorCategoryModel {
    default String getName() {
        return nameProperty().getValue();
    }

    StringProperty nameProperty();

    default void setName(String value) {
        nameProperty().setValue(value);
    }

    default boolean isSelected() {
        return selectedProperty().getValue();
    }

    BooleanProperty selectedProperty();

    default void setSelected(boolean value) {
        selectedProperty().setValue(value);
    }

    default boolean isValid() {
        return validProperty().getValue();
    }

    BooleanProperty validProperty();

    default void setValid(boolean value) {
        validProperty().setValue(value);
    }

    ListEditorModel getEditor();

    ObservableList<EditorEntryModel> getEntries();

    void updateValidity();
}
