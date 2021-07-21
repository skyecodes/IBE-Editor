package com.github.franckyi.ibeeditor.impl.client.mvc.base.controller;

import com.github.franckyi.databindings.api.event.ObservableListChangeEvent;
import com.github.franckyi.databindings.api.event.ObservableListChangeListener;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.Guapi;
import com.github.franckyi.guapi.api.mvc.AbstractController;
import com.github.franckyi.ibeeditor.api.client.mvc.base.controller.ListEditorController;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorEntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.ListEditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.view.ListEditorView;

import static com.github.franckyi.guapi.GuapiHelper.*;

public abstract class AbstractListEditorController<M extends ListEditorModel, V extends ListEditorView> extends AbstractController<M, V> implements ListEditorController<M, V> {
    private static final Text FIX_ERRORS = translated("ibeeditor.gui.fix_errors").red();
    private final ObservableListChangeListener<EditorEntryModel> listener = this::onSelectedCategoryEntryChange;

    public AbstractListEditorController(M model, V view) {
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

    private void updateEntryList(EditorCategoryModel oldValue, EditorCategoryModel newValue) {
        if (oldValue != null) {
            oldValue.getEntries().removeListener(listener);
        }
        if (newValue != null) {
            model.getSelectedCategory().getEntries().addListener(listener);
            onSelectedCategoryEntryChange(null);
        }
    }

    private void onSelectedCategoryEntryChange(ObservableListChangeEvent<? extends EditorEntryModel> event) {
        view.getEntryList().getItems().setAll(model.getSelectedCategory().getEntries());
    }

    protected void onValidationChange(boolean newVal) {
        view.getDoneButton().setDisable(!newVal);
        if (!newVal) {
            view.getDoneButton().setTooltip(FIX_ERRORS);
        } else {
            view.getDoneButton().setTooltip(null);
        }
    }
}
