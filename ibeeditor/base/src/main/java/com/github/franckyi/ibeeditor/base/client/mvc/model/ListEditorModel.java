package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.guapi.api.mvc.Model;
import com.github.franckyi.ibeeditor.base.client.util.texteditor.TextEditorActionHandler;

public abstract class ListEditorModel<C extends EditorCategoryModel> implements Model {
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

    public abstract void apply();

    public ObservableList<C> getCategories() {
        return categories;
    }

    public EditorCategoryModel getSelectedCategory() {
        return selectedCategoryProperty().getValue();
    }

    public ObjectProperty<EditorCategoryModel> selectedCategoryProperty() {
        return selectedCategory;
    }

    public void setSelectedCategory(EditorCategoryModel value) {
        selectedCategoryProperty().setValue(value);
    }

    public boolean isValid() {
        return validProperty().getValue();
    }

    public BooleanProperty validProperty() {
        return validProperty;
    }

    public void setValid(boolean value) {
        validProperty().setValue(value);
    }

    public TextEditorActionHandler getActiveTextEditor() {
        return activeTextEditorProperty().getValue();
    }

    public ObjectProperty<TextEditorActionHandler> activeTextEditorProperty() {
        return activeTextEditorProperty;
    }

    public void setActiveTextEditor(TextEditorActionHandler value) {
        activeTextEditorProperty().setValue(value);
    }

    public void updateValidity() {
        setValid(getCategories().stream().allMatch(EditorCategoryModel::isValid));
    }
}
