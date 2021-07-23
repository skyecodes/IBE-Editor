package com.github.franckyi.ibeeditor.base.client.mvc;

import com.github.franckyi.guapi.api.mvc.SimpleMVC;
import com.github.franckyi.ibeeditor.base.client.mvc.controller.EditorCategoryController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.EditorCategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.EditorCategoryView;

public class EditorCategoryMVC implements SimpleMVC<EditorCategoryModel, EditorCategoryView, EditorCategoryController> {
    public static final EditorCategoryMVC INSTANCE = new EditorCategoryMVC();

    private EditorCategoryMVC() {
    }

    @Override
    public EditorCategoryView createView() {
        return new EditorCategoryView();
    }

    @Override
    public EditorCategoryController createController(EditorCategoryModel model, EditorCategoryView view) {
        return new EditorCategoryController(model, view);
    }
}
