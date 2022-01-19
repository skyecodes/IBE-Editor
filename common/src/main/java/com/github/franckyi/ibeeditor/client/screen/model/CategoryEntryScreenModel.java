package com.github.franckyi.ibeeditor.client.screen.model;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableBooleanValue;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.guapi.api.mvc.Model;
import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import com.github.franckyi.ibeeditor.client.util.texteditor.TextEditorActionHandler;

public abstract class CategoryEntryScreenModel<C extends CategoryModel> implements Model {
    protected final ObservableBooleanValue validProperty;
    protected final ObservableList<C> categories = ObservableList.create();
    protected final ObjectProperty<CategoryModel> selectedCategory = ObjectProperty.create();
    protected final ObjectProperty<TextEditorActionHandler> activeTextEditorProperty = ObjectProperty.create();
    protected final StringProperty textEditorCustomColorProperty = StringProperty.create("#ffffff");

    public CategoryEntryScreenModel() {
        validProperty = getCategories().allMatch(CategoryModel::isValid, CategoryModel::validProperty);
    }

    @Override
    public void initalize() {
        setupCategories();
        if (getCategories().size() > 0) {
            setSelectedCategory(getCategories().get(0));
        }
    }

    protected abstract void setupCategories();

    public abstract void apply();

    public void update() {
        apply();
        Guapi.getScreenHandler().hideScene();
    }

    public ObservableList<C> getCategories() {
        return categories;
    }

    public CategoryModel getSelectedCategory() {
        return selectedCategoryProperty().getValue();
    }

    public ObjectProperty<CategoryModel> selectedCategoryProperty() {
        return selectedCategory;
    }

    public void setSelectedCategory(CategoryModel value) {
        selectedCategoryProperty().setValue(value);
    }

    public boolean isValid() {
        return validProperty().getValue();
    }

    public ObservableBooleanValue validProperty() {
        return validProperty;
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

    public String getTextEditorCustomColor() {
        return textEditorCustomColor().getValue();
    }

    public StringProperty textEditorCustomColor() {
        return textEditorCustomColorProperty;
    }

    public void setTextEditorCustomColor(String value) {
        textEditorCustomColor().setValue(value);
    }
}
