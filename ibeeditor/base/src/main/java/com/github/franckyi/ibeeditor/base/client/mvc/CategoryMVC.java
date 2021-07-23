package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.CategoryController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.CategoryView;

public final class CategoryMVC extends SimpleMVC<CategoryModel, CategoryView, CategoryController> {
    public static final CategoryMVC INSTANCE = new CategoryMVC();

    private CategoryMVC() {
        super(CategoryView::new, CategoryController::new);
    }
}
