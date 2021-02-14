package com.github.franckyi.ibeeditor.api.client.mvc;

import com.github.franckyi.guapi.api.mvc.MVC;
import com.github.franckyi.ibeeditor.api.client.mvc.controller.CategoryController;
import com.github.franckyi.ibeeditor.api.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.view.CategoryView;

public interface CategoryMVC extends MVC<CategoryModel, CategoryView, CategoryController> {
}
