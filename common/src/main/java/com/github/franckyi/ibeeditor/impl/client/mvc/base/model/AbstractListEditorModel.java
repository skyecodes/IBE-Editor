package com.github.franckyi.ibeeditor.impl.client.mvc.base.model;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.ListEditorModel;
import com.github.franckyi.ibeeditor.impl.client.util.texteditor.TextEditorActionHandler;

public abstract class AbstractListEditorModel<C extends EditorCategoryModel> implements ListEditorModel {
    protected final BooleanProperty validProperty = DataBindings.getPropertyFactory().createBooleanProperty(true);
    protected final ObservableList<C> categories = DataBindings.getObservableListFactory().createObservableArrayList();
    protected final ObjectProperty<EditorCategoryModel> selectedCategory = DataBindings.getPropertyFactory().createObjectProperty();
    protected final ObjectProperty<TextEditorActionHandler> activeTextEditorProperty = DataBindings.getPropertyFactory().createObjectProperty();

    @Override
    public void initalize() {
        setupCategories();
        selectedCategoryProperty().addListener((oldVal, newVal) -> {
            if (oldVal != null) {
                oldVal.setSelected(false);
            }
            if (newVal != null) {
                newVal.setSelected(true);
            }
        });
        if (getCategories().size() > 0) {
            setSelectedCategory(getCategories().get(0));
            updateValidity();
        }
        getCategories().addListener(this::updateValidity);
    }

    protected abstract void setupCategories();

    @Override
    public ObservableList<C> getCategories() {
        return categories;
    }

    @Override
    public ObjectProperty<EditorCategoryModel> selectedCategoryProperty() {
        return selectedCategory;
    }

    @Override
    public ObjectProperty<TextEditorActionHandler> activeTextEditorProperty() {
        return activeTextEditorProperty;
    }

    @Override
    public BooleanProperty validProperty() {
        return validProperty;
    }
}
