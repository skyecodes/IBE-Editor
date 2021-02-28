package com.github.franckyi.ibeeditor.api.client.mvc.editor.model;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.ibeeditor.api.client.mvc.model.EntryModel;

public interface CategoryModel {
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

    EditorModel getEditor();

    ObservableList<EntryModel> getEntries();
}
