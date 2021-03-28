package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.CategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EntryModel;

public abstract class AbstractCategoryModel implements CategoryModel {
    private final StringProperty nameProperty;
    private final BooleanProperty selectedProperty = Bindings.getPropertyFactory().ofBoolean(false);
    private final BooleanProperty validProperty = Bindings.getPropertyFactory().ofBoolean(true);
    private final ObservableList<EntryModel> entries = Bindings.getObservableListFactory().arrayList();
    private final EditorModel editor;

    public AbstractCategoryModel(String name, EditorModel editor) {
        nameProperty = Bindings.getPropertyFactory().ofString(name);
        this.editor = editor;
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
    public EditorModel getEditor() {
        return editor;
    }

    @Override
    public ObservableList<EntryModel> getEntries() {
        return entries;
    }
}
