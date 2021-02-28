package com.github.franckyi.ibeeditor.api.client.mvc.editor.model;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;

public interface EditorModel {
    ObservableList<CategoryModel> getCategories();

    default CategoryModel getSelectedCategory() {
        return selectedCategoryProperty().getValue();
    }

    ObjectProperty<CategoryModel> selectedCategoryProperty();

    default void setSelectedCategory(CategoryModel value) {
        selectedCategoryProperty().setValue(value);
    }
}
