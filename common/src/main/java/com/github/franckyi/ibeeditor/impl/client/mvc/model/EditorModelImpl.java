package com.github.franckyi.ibeeditor.impl.client.mvc.model;

import com.github.franckyi.databindings.ObservableListFactory;
import com.github.franckyi.databindings.PropertyFactory;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.ibeeditor.api.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.model.EditorModel;

public class EditorModelImpl implements EditorModel {
    private final ObjectProperty<CategoryModel> selectedCategory = PropertyFactory.ofObject();
    private final ObservableList<CategoryModel> categories = ObservableListFactory.arrayList();

    public EditorModelImpl() {
        selectedCategoryProperty().addListener((oldVal, newVal) -> {
            if (oldVal != null) {
                oldVal.setSelected(false);
            }
            if (newVal != null) {
                newVal.setSelected(true);
            }
        });
        getCategories().addListener(() -> {
            if (!selectedCategoryProperty().hasValue() && getCategories().size() > 0) {
                setSelectedCategory(getCategories().get(0));
            }
        });
    }

    @Override
    public ObservableList<CategoryModel> getCategories() {
        return categories;
    }

    @Override
    public ObjectProperty<CategoryModel> selectedCategoryProperty() {
        return selectedCategory;
    }
}
