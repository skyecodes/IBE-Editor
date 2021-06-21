package com.github.franckyi.ibeeditor.api.client.mvc.base.model;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.entry.TextEditorEntryController;

public interface ListEditorModel {
    ObservableList<? extends EditorCategoryModel> getCategories();

    default EditorCategoryModel getSelectedCategory() {
        return selectedCategoryProperty().getValue();
    }

    ObjectProperty<EditorCategoryModel> selectedCategoryProperty();

    default void setSelectedCategory(EditorCategoryModel value) {
        selectedCategoryProperty().setValue(value);
    }

    default boolean isValid() {
        return validProperty().getValue();
    }

    BooleanProperty validProperty();

    default void setValid(boolean value) {
        validProperty().setValue(value);
    }

    default TextEditorEntryController getFocusedTextEntry() {
        return focusedTextEntryProperty().getValue();
    }

    ObjectProperty<TextEditorEntryController> focusedTextEntryProperty();

    default void setFocusedTextEntry(TextEditorEntryController value) {
        focusedTextEntryProperty().setValue(value);
    }

    void apply();

    void updateValidity();
}
