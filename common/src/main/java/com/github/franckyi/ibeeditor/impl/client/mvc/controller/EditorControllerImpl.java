package com.github.franckyi.ibeeditor.impl.client.mvc.controller;

import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.ibeeditor.api.client.mvc.controller.EditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.model.EditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.view.EditorView;

public class EditorControllerImpl implements EditorController {
    public static final EditorController INSTANCE = new EditorControllerImpl();

    private EditorControllerImpl() {
    }

    @Override
    public void init(EditorModel model, EditorView view) {
        updateCategoryList(model, view);
        updateEntryList(model, view);
        model.getCategories().addListener(() -> this.updateCategoryList(model, view));
        model.selectedCategoryProperty().addListener(() -> this.updateEntryList(model, view));
        view.getDoneButton().onAction(event -> GUAPI.getScreenHandler().hide());
        view.getCancelButton().onAction(event -> GUAPI.setDebugMode(!GUAPI.isDebugMode()));
    }

    private void updateCategoryList(EditorModel model, EditorView view) {
        view.getCategoryList().getItems().setAll(model.getCategories());
    }

    private void updateEntryList(EditorModel model, EditorView view) {
        if (model.selectedCategoryProperty().hasValue()) {
            view.getEntryList().getItems().setAll(model.getSelectedCategory().getEntries());
        }
    }
}
