package com.github.franckyi.ibeeditor.base.client.mvc.base.model;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorCategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.EditorEntryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.base.model.ListEditorModel;
import com.github.franckyi.ibeeditor.base.client.mvc.base.model.entry.AddListEntryEditorEntryModel;

import java.util.Collections;

public abstract class AbstractEditorCategoryModel implements EditorCategoryModel {
    private final StringProperty nameProperty;
    private final BooleanProperty selectedProperty = DataBindings.getPropertyFactory().createBooleanProperty(false);
    private final BooleanProperty validProperty = DataBindings.getPropertyFactory().createBooleanProperty(true);
    private final ObservableList<EditorEntryModel> entries = DataBindings.getObservableListFactory().createObservableArrayList();
    private final ListEditorModel editor;

    protected AbstractEditorCategoryModel(String name, ListEditorModel editor) {
        nameProperty = DataBindings.getPropertyFactory().createStringProperty(name);
        this.editor = editor;
    }

    @Override
    public void initalize() {
        setupEntries();
        if (hasEntryList()) {
            getEntries().add(new AddListEntryEditorEntryModel(this, getAddListEntryButtonTooltip()));
        }
        updateValidity();
        validProperty().addListener(getEditor()::updateValidity);
        getEntries().addListener(this::updateValidity);
    }

    protected abstract void setupEntries();

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
        if (hasEntryList()) {
            for (int i = 0; i < getEntryListSize(); i++) {
                EditorEntryModel entry = getEntries().get(getEntryListIndex(i));
                entry.setListSize(getEntryListSize());
                entry.setListIndex(i);
            }
        }
    }

    @Override
    public void moveEntryUp(int index) {
        Collections.swap(getEntries(), getEntryListIndex(index), getEntryListIndex(index) - 1);
    }

    @Override
    public void moveEntryDown(int index) {
        Collections.swap(getEntries(), getEntryListIndex(index), getEntryListIndex(index) + 1);
    }

    @Override
    public void deleteEntry(int index) {
        getEntries().remove(getEntryListIndex(index));
    }
}
