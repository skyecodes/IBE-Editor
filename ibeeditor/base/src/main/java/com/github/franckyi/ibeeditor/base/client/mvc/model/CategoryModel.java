package com.github.franckyi.ibeeditor.base.client.mvc.model;

import com.github.franckyi.databindings.DataBindings;
import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.guapi.api.mvc.Model;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.AddListEntryEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.EntryModel;

import java.util.Collections;

import static com.github.franckyi.guapi.GuapiHelper.*;

public abstract class CategoryModel implements Model {
    private final StringProperty nameProperty;
    private final BooleanProperty selectedProperty = BooleanProperty.create(false);
    private final BooleanProperty validProperty = BooleanProperty.create(true);
    private final ObservableList<EntryModel> entries = ObservableList.create();
    private final ListEditorModel<?> editor;

    protected CategoryModel(String name, ListEditorModel<?> editor) {
        nameProperty = StringProperty.create(name);
        this.editor = editor;
    }

    @Override
    public void initalize() {
        setupEntries();
        if (hasEntryList()) {
            getEntries().add(new AddListEntryEntryModel(this, getAddListEntryButtonTooltip()));
        }
        updateValidity();
        validProperty().addListener(getEditor()::updateValidity);
        getEntries().addListener(this::updateValidity);
    }

    protected abstract void setupEntries();

    public String getName() {
        return nameProperty().getValue();
    }

    public StringProperty nameProperty() {
        return nameProperty;
    }

    public void setName(String value) {
        nameProperty().setValue(value);
    }

    public boolean isSelected() {
        return selectedProperty().getValue();
    }

    public BooleanProperty selectedProperty() {
        return selectedProperty;
    }

    public void setSelected(boolean value) {
        selectedProperty().setValue(value);
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

    public ListEditorModel<?> getEditor() {
        return editor;
    }

    public ObservableList<EntryModel> getEntries() {
        return entries;
    }

    public void updateValidity() {
        setValid(getEntries().stream().allMatch(EntryModel::isValid));
        if (hasEntryList()) {
            for (int i = 0; i < getEntryListSize(); i++) {
                EntryModel entry = getEntries().get(getEntryListIndex(i));
                entry.setListSize(getEntryListSize());
                entry.setListIndex(i);
            }
        }
    }

    public int getEntryListStart() {
        return -1;
    }

    private int getEntryListIndex(int index) {
        return getEntryListStart() + index;
    }

    private int getEntryListSize() {
        return getEntries().size() - getEntryListStart() - 1;
    }

    private boolean hasEntryList() {
        return getEntryListStart() >= 0;
    }

    public void addEntryInList() {
        getEntries().add(getEntries().size() - 1, createListEntry());
    }

    public EntryModel createListEntry() {
        return null;
    }

    protected Text getAddListEntryButtonTooltip() {
        return translated("ibeeditor.gui.add").green();
    }

    public void moveEntryUp(int index) {
        Collections.swap(getEntries(), getEntryListIndex(index), getEntryListIndex(index) - 1);
    }

    public void moveEntryDown(int index) {
        Collections.swap(getEntries(), getEntryListIndex(index), getEntryListIndex(index) + 1);
    }

    public void deleteEntry(int index) {
        getEntries().remove(getEntryListIndex(index));
    }

    public int getEntryHeight() {
        return 25;
    }
}
