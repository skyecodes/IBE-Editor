package com.github.franckyi.ibeeditor.impl.client.mvc.editor;

import com.github.franckyi.ibeeditor.api.client.mvc.editor.CategoryMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.controller.CategoryController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.CategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.view.CategoryView;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.controller.CategoryControllerImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.editor.view.CategoryViewImpl;

public class CategoryMVCImpl implements CategoryMVC {
    public static final CategoryMVC INSTANCE = new CategoryMVCImpl();

    private CategoryMVCImpl() {
    }

    @Override
    public CategoryView createView() {
        return new CategoryViewImpl();
    }

    @Override
    public CategoryController createController(CategoryModel model, CategoryView view) {
        return new CategoryControllerImpl(model, view);
    }
}
