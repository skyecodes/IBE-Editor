package com.github.franckyi.ibeeditor.client.screen.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.client.screen.controller.CategoryController;
import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import com.github.franckyi.ibeeditor.client.screen.view.CategoryView;

public final class CategoryMVC extends SimpleMVC<CategoryModel, CategoryView, CategoryController> {
    public static final CategoryMVC INSTANCE = new CategoryMVC();

    private CategoryMVC() {
        super(CategoryView::new, CategoryController::new);
    }
}
