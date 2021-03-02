package com.github.franckyi.ibeeditor.api.client.mvc.editor;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.controller.CategoryController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.CategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.view.CategoryView;

public interface CategoryMVC extends SimpleMVC<CategoryModel, CategoryView, CategoryController> {
}
