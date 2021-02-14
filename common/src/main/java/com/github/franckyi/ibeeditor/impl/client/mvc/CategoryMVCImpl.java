package com.github.franckyi.ibeeditor.impl.client.mvc;

import com.github.franckyi.ibeeditor.api.client.mvc.CategoryMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.controller.CategoryController;
import com.github.franckyi.ibeeditor.api.client.mvc.view.CategoryView;
import com.github.franckyi.ibeeditor.impl.client.mvc.controller.CategoryControllerImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.view.CategoryViewImpl;

public class CategoryMVCImpl implements CategoryMVC {
    public static final CategoryMVC INSTANCE = new CategoryMVCImpl();

    private CategoryMVCImpl() {
    }

    @Override
    public CategoryView createView() {
        return new CategoryViewImpl();
    }

    @Override
    public CategoryController getController() {
        return CategoryControllerImpl.INSTANCE;
    }
}
