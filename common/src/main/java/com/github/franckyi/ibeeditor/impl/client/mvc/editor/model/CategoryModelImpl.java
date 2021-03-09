package com.github.franckyi.ibeeditor.impl.client.mvc.editor.model;

import com.github.franckyi.databindings.Bindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.CategoryModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EditorModel;
import com.github.franckyi.ibeeditor.api.client.mvc.editor.model.EntryModel;

public class CategoryModelImpl implements CategoryModel {
    private final StringProperty nameProperty = Bindings.getPropertyFactory().ofString("");
    private final BooleanProperty selectedProperty = Bindings.getPropertyFactory().ofBoolean(false);
    private final ObservableList<EntryModel> entries = Bindings.getObservableListFactory().arrayList();
    private final EditorModel editor;

    public CategoryModelImpl(String name, EditorModel editor) {
        this.editor = editor;
        setName(name);
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
    public EditorModel getEditor() {
        return editor;
    }

    @Override
    public ObservableList<EntryModel> getEntries() {
        return entries;
    }
}
