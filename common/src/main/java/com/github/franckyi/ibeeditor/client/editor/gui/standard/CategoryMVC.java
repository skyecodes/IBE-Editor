package com.github.franckyi.ibeeditor.client.editor.gui.standard;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;

public final class CategoryMVC extends SimpleMVC<CategoryModel, CategoryView, CategoryController> {
    public static final CategoryMVC INSTANCE = new CategoryMVC();

    private CategoryMVC() {
        super(CategoryView::new, CategoryController::new);
    }
}
