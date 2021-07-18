package com.github.franckyi.ibeeditor.api.client.mvc.base.model;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.databindings.api.StringProperty;
import com.github.franckyi.guapi.api.mvc.Model;
import com.github.franckyi.minecraft.api.common.text.Text;

public interface EditorCategoryModel extends Model {
    default String getName() {
        return nameProperty().getValue();
    }

    StringProperty nameProperty();

    default void setName(String value) {
        nameProperty().setValue(value);
    }

    default boolean isSelected() {
        return selectedProperty().getValue();
    }

    BooleanProperty selectedProperty();

    default void setSelected(boolean value) {
        selectedProperty().setValue(value);
    }

    default boolean isValid() {
        return validProperty().getValue();
    }

    BooleanProperty validProperty();

    default void setValid(boolean value) {
        validProperty().setValue(value);
    }

    ListEditorModel getEditor();

    ObservableList<EditorEntryModel> getEntries();

    void updateValidity();

    default int getEntryListStart() {
        return -1;
    }

    default int getEntryListIndex(int index) {
        return getEntryListStart() + index;
    }

    default int getEntryListSize() {
        return getEntries().size() - getEntryListStart() - 1;
    }

    default boolean hasEntryList() {
        return getEntryListStart() >= 0;
    }

    default void addEntryInList() {
        getEntries().add(getEntries().size() - 1, createListEntry());
    }

    default EditorEntryModel createListEntry() {
        return null;
    }

    default Text getAddListEntryButtonTooltip() {
        return Text.createTranslatedText("ibeeditor.gui.add");
    }

    void moveEntryUp(int index);

    void moveEntryDown(int indexl);

    void deleteEntry(int index);
}
