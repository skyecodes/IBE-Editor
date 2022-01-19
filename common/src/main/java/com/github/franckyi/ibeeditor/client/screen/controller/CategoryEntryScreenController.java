package com.github.franckyi.ibeeditor.client.screen.controller;

import com.github.franckyi.databindings.api.event.ObservableListChangeEvent;
import com.github.franckyi.databindings.api.event.ObservableListChangeListener;
import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.client.screen.model.CategoryEntryScreenModel;
import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.EntryModel;
import com.github.franckyi.ibeeditor.client.screen.view.CategoryEntryScreenView;
import com.github.franckyi.ibeeditor.common.ModTexts;

public abstract class CategoryEntryScreenController<M extends CategoryEntryScreenModel<?>, V extends CategoryEntryScreenView> extends AbstractController<M, V> {
    private final ObservableListChangeListener<EntryModel> listener = this::onSelectedCategoryEntryChange;

    public CategoryEntryScreenController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        updateCategoryList();
        updateEntryList(null, model.getSelectedCategory());
        model.getCategories().addListener(this::updateCategoryList);
        model.selectedCategoryProperty().addListener(this::updateEntryList);
        model.validProperty().addListener(this::updateDoneButton);
        view.getDoneButton().onAction(model::update);
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

    protected void updateDoneButton() {
        view.getDoneButton().setDisable(!model.isValid());
        if (!model.isValid()) {
            view.getDoneButton().getTooltip().setAll(ModTexts.FIX_ERRORS);
        } else {
            view.getDoneButton().getTooltip().clear();
        }
    }
}
