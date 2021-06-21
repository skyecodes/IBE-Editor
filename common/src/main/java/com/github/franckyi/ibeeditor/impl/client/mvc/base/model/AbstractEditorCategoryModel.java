package com.github.franckyi.ibeeditor.impl.client.mvc.base.model;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorEntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.ListEditorModel;

public abstract class AbstractEditorCategoryModel implements EditorCategoryModel {
    private final StringProperty nameProperty;
    private final BooleanProperty selectedProperty = DataBindings.getPropertyFactory().createBooleanProperty(false);
    private final BooleanProperty validProperty = DataBindings.getPropertyFactory().createBooleanProperty(true);
    private final ObservableList<EditorEntryModel> entries = DataBindings.getObservableListFactory().createObservableArrayList();
    private final ListEditorModel editor;

    public AbstractEditorCategoryModel(String name, ListEditorModel editor) {
        nameProperty = DataBindings.getPropertyFactory().createStringProperty(name);
        this.editor = editor;
        validProperty().addListener(() -> getEditor().updateValidity());
        getEntries().addListener(this::updateValidity);
    }

    @Override
    public StringProperty nameProperty() {
        return nameProperty;
    }

    @Override
    public BooleanProperty selectedProperty() {
        return selectedProperty;
    }

    @Override
    public BooleanProperty validProperty() {
        return validProperty;
    }

    @Override
    public ListEditorModel getEditor() {
        return editor;
    }

    @Override
    public ObservableList<EditorEntryModel> getEntries() {
        return entries;
    }

    @Override
    public void updateValidity() {
        setValid(getEntries().stream().allMatch(EditorEntryModel::isValid));
    }
}
