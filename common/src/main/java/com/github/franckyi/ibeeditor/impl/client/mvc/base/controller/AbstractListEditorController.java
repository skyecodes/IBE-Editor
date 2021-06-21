package com.github.franckyi.ibeeditor.impl.client.mvc.base.controller;

import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.api.client.mvc.base.controller.ListEditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.ListEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.view.ListEditorView;
import com.github.franckyi.minecraft.Minecraft;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public abstract class AbstractListEditorController<M extends ListEditorModel, V extends ListEditorView> extends AbstractController<M, V> implements ListEditorController<M, V> {
    public AbstractListEditorController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        updateCategoryList();
        updateEntryList();
        model.getCategories().addListener(this::updateCategoryList);
        model.selectedCategoryProperty().addListener(this::updateEntryList);
        view.getDoneButton().onAction(event -> model.apply());
        model.validProperty().addListener(this::onValidationChange);
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

    protected void onValidationChange(boolean newVal) {
        view.getDoneButton().setDisable(!newVal);
        if (!newVal) {
            view.getDoneButton().setTooltip(text("You must fix errors before applying changes.").red());
        } else {
            view.getDoneButton().setTooltip(null);
        }
    }
}
