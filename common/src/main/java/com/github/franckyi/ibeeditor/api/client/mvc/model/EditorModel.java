package com.github.franckyi.ibeeditor.api.client.mvc.model;

import com.github.franckyi.databindings.api.ObservableList;

public interface EditorModel {
    ObservableList<CategoryModel> getCategories();
}
