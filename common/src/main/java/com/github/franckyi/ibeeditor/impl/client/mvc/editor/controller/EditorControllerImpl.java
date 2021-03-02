package com.github.franckyi.ibeeditor.impl.client.mvc.editor.controller;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.guapi.GUAPI;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.controller.EditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.view.EditorView;

public class EditorControllerImpl extends AbstractController<EditorModel, EditorView> implements EditorController {
    public EditorControllerImpl(EditorModel model, EditorView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        updateCategoryList();
        updateEntryList();
        model.getCategories().addListener(this::updateCategoryList);
        model.selectedCategoryProperty().addListener(this::updateEntryList);
        view.getDoneButton().onAction(event -> GameHooks.client().getScreenHandler().hideScene());
        view.getCancelButton().onAction(event -> GUAPI.setDebugMode(!GUAPI.isDebugMode()));
    }

    private void updateCategoryList() {
        view.getCategoryList().getItems().setAll(model.getCategories());
    }

    private void updateEntryList() {
        if (model.selectedCategoryProperty().hasValue()) {
            view.getEntryList().getItems().setAll(model.getSelectedCategory().getEntries());
        }
    }
}
