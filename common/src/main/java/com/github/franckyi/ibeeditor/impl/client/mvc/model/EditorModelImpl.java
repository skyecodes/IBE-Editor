package com.github.franckyi.ibeeditor.impl.client.mvc.model;

import com.github.franckyi.databindings.ObservableListFactory;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.ibeeditor.api.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.model.EditorModel;

public class EditorModelImpl implements EditorModel {
    private final ObservableList<CategoryModel> categories = ObservableListFactory.arrayList();

    public EditorModelImpl() {
    }

    @Override
    public ObservableList<CategoryModel> getCategories() {
        return categories;
    }
}
