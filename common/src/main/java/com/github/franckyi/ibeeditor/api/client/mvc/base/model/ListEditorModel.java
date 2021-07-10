package com.github.franckyi.ibeeditor.api.client.mvc.base.model;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.ibeeditor.impl.client.util.texteditor.TextEditorActionHandler;

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

    default TextEditorActionHandler getActiveTextEditor() {
        return activeTextEditorProperty().getValue();
    }

    ObjectProperty<TextEditorActionHandler> activeTextEditorProperty();

    default void setActiveTextEditor(TextEditorActionHandler value) {
        activeTextEditorProperty().setValue(value);
    }

    void apply();

    void updateValidity();
}
