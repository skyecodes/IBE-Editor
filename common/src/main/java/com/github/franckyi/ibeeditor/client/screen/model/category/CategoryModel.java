package com.github.franckyi.ibeeditor.client.screen.model.category;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.databindings.api.ObservableList;
import com.github.franckyi.guapi.api.mvc.Model;
import com.github.franckyi.ibeeditor.client.screen.model.CategoryEntryScreenModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.AddListEntryEntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.entry.EntryModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.Collections;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public abstract class CategoryModel implements Model {
    private final ObjectProperty<Component> nameProperty;
    private final BooleanProperty selectedProperty = BooleanProperty.create(false);
    private final BooleanProperty validProperty = BooleanProperty.create(true);
    private final ObservableList<EntryModel> entries = ObservableList.create();
    private final CategoryEntryScreenModel<?> parent;

    protected CategoryModel(Component name, CategoryEntryScreenModel<?> parent) {
        this.nameProperty = ObjectProperty.create(name);
        this.parent = parent;
    }

    @Override
    public void initalize() {
        setupEntries();
        updateValidity();
        if (hasEntryList()) {
            getEntries().add(new AddListEntryEntryModel(this, ModTexts.addListEntry(getAddListEntryButtonTooltip()).withStyle(ChatFormatting.GREEN)));
            updateEntryListIndexes();
        }
        validProperty().addListener(getParent()::updateValidity);
        getEntries().addListener(() -> {
            updateValidity();
            updateEntryListIndexes();
        });
    }

    protected abstract void setupEntries();

    public Component getName() {
        return nameProperty().getValue();
    }

    public ObjectProperty<Component> nameProperty() {
        return nameProperty;
    }

    public void setName(Component value) {
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

    public CategoryEntryScreenModel<?> getParent() {
        return parent;
    }

    public ObservableList<EntryModel> getEntries() {
        return entries;
    }

    public void updateValidity() {
        setValid(getEntries().stream().allMatch(EntryModel::isValid));
    }

    public void updateEntryListIndexes() {
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
        getEntries().add(getEntries().size() - 1, createNewListEntry());
    }

    public EntryModel createNewListEntry() {
        return null;
    }

    protected MutableComponent getAddListEntryButtonTooltip() {
        return EMPTY_TEXT;
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
