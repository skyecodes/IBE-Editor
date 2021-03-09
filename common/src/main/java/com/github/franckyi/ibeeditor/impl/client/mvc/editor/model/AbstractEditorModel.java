package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.CategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EditorModel;

public abstract class AbstractEditorModel implements EditorModel {
    protected final ObservableList<CategoryModel> categories = Bindings.getObservableListFactory().arrayList();
    protected final ObjectProperty<CategoryModel> selectedCategory = Bindings.getPropertyFactory().ofObject();

    protected AbstractEditorModel() {
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
