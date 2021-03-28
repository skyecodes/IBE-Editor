package com.github.franckyi.ibeeditor.api.client.mvc.editor.model;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.minecraft.api.common.text.Text;

public interface EditorModel {
    ObservableList<? extends CategoryModel> getCategories();

    default CategoryModel getSelectedCategory() {
        return selectedCategoryProperty().getValue();
    }

    ObjectProperty<CategoryModel> selectedCategoryProperty();

    default void setSelectedCategory(CategoryModel value) {
        selectedCategoryProperty().setValue(value);
    }

    Text getDisabledTooltip();

    default boolean isDisabled() {
        return getDisabledTooltip() != null;
    }

    void apply();
}
