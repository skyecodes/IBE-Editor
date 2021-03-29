package com.github.franckyi.ibeeditor.impl.client.mvc.editor.controller;

import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.controller.EditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.view.EditorView;
import com.github.franckyi.minecraft.Minecraft;

import static com.github.franckyi.guapi.GUAPIHelper.*;

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
        view.getDoneButton().onAction(event -> model.apply());
        if (model.isDisabled()) {
            view.getDoneButton().setDisable(true);
            view.getDoneButton().setTooltip(model.getDisabledTooltip());
        } else {
            model.validProperty().addListener(newVal -> {
                view.getDoneButton().setDisable(!newVal);
                if (!newVal) {
                    view.getDoneButton().setTooltip(text("You must fix errors before applying changes.", RED));
                } else {
                    view.getDoneButton().setTooltip(null);
                }
            });
        }
        view.getCancelButton().onAction(Minecraft.getClient().getScreenHandler()::hideScene);
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
