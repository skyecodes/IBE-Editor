package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.databindings.api.event.ObservableListChangeEvent;
import com.github.franckyi.databindings.api.event.ObservableListChangeListener;
import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ListEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ListEditorView;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

public abstract class ListEditorController<M extends ListEditorModel<?>, V extends ListEditorView> extends AbstractController<M, V> {
    private final ObservableListChangeListener<EntryModel> listener = this::onSelectedCategoryEntryChange;

    public ListEditorController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        updateCategoryList();
        updateEntryList(null, model.getSelectedCategory());
        model.getCategories().addListener(this::updateCategoryList);
        model.selectedCategoryProperty().addListener(this::updateEntryList);
        view.getDoneButton().onAction(event -> model.apply());
        model.validProperty().addListener(this::onValidationChange);
        view.getCancelButton().onAction(Guapi.getScreenHandler()::hideScene);
    }

    private void updateCategoryList() {
        view.getCategoryList().getItems().setAll(model.getCategories());
    }

    private void updateEntryList(CategoryModel oldValue, CategoryModel newValue) {
        if (oldValue != null) {
            oldValue.getEntries().removeListener(listener);
        }
        if (newValue != null) {
            model.getSelectedCategory().getEntries().addListener(listener);
            onSelectedCategoryEntryChange(null);
        }
    }

    private void onSelectedCategoryEntryChange(ObservableListChangeEvent<? extends EntryModel> event) {
        view.getEntryList().getItems().setAll(model.getSelectedCategory().getEntries());
        view.getEntryList().setItemHeight(model.getSelectedCategory().getEntryHeight());
    }

    protected void onValidationChange(boolean newVal) {
        view.getDoneButton().setDisable(!newVal);
        if (!newVal) {
            if (view.getDoneButton().getTooltip().isEmpty()) {
                view.getDoneButton().getTooltip().add(ModTexts.FIX_ERRORS);
            } else {
                view.getDoneButton().getTooltip().set(0, ModTexts.FIX_ERRORS);
            }
        } else {
            view.getDoneButton().getTooltip().clear();
        }
    }
}
