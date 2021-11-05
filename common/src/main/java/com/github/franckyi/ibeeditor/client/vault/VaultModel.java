package com.github.franckyi.ibeeditor.client.vault;

import com.github.franckyi.databindings.api.ObjectProperty;
import com.github.franckyi.guapi.api.mvc.Model;
import com.google.common.collect.Multimap;

import java.util.function.Consumer;

public class VaultModel implements Model {
    private final Multimap<VaultItemType, VaultItemModel> items;
    private final ObjectProperty<VaultItemType> selectedTypeProperty;
    private final Consumer<VaultItemModel> action;
    private final ObjectProperty<VaultItemModel> selectedItemProperty;
    private final boolean selection;

    public VaultModel() {
        this(null, null, false);
    }

    public VaultModel(VaultItemType type, Consumer<VaultItemModel> action) {
        this(type, action, true);
    }

    public VaultModel(VaultItemType type, Consumer<VaultItemModel> action, boolean selection) {
        this.items = VaultManager.getItems();
        this.selectedTypeProperty = ObjectProperty.create(type);
        this.action = action;
        this.selection = selection;
        selectedItemProperty = ObjectProperty.create();
    }

    public Multimap<VaultItemType, VaultItemModel> getItems() {
        return items;
    }

    public VaultItemType getSelectedType() {
        return selectedTypeProperty().get();
    }

    public ObjectProperty<VaultItemType> selectedTypeProperty() {
        return selectedTypeProperty;
    }

    public void setSelectedType(VaultItemType value) {
        selectedTypeProperty().setValue(value);
    }

    public Consumer<VaultItemModel> getAction() {
        return action;
    }

    public VaultItemModel getSelectedItem() {
        return selectedItemProperty().get();
    }

    public ObjectProperty<VaultItemModel> selectedItemProperty() {
        return selectedItemProperty;
    }

    public void setSelectedItem(VaultItemModel value) {
        selectedItemProperty().setValue(value);
    }

    public boolean isSelection() {
        return selection;
    }
}
