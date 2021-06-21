package com.github.franckyi.ibeeditor.impl.client.mvc.base;

import com.github.franckyi.ibeeditor.api.client.mvc.base.EditorCategoryMVC;
import com.github.franckyi.ibeeditor.api.client.mvc.base.controller.EditorCategoryController;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.view.EditorCategoryView;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.EditorCategoryControllerImpl;
import com.github.franckyi.ibeeditor.impl.client.mvc.base.view.EditorCategoryViewImpl;

public class EditorCategoryMVCImpl implements EditorCategoryMVC {
    public static final EditorCategoryMVC INSTANCE = new EditorCategoryMVCImpl();

    private EditorCategoryMVCImpl() {
    }

    @Override
    public EditorCategoryView createView() {
        return new EditorCategoryViewImpl();
    }

    @Override
    public EditorCategoryController createController(EditorCategoryModel model, EditorCategoryView view) {
        return new EditorCategoryControllerImpl(model, view);
    }
}
