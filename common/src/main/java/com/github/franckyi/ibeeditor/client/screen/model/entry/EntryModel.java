package com.github.franckyi.ibeeditor.client.screen.model.entry;

import com.github.franckyi.databindings.api.BooleanProperty;
import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.guapi.api.mvc.Model;
import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;

public abstract class EntryModel implements Model {
    private final CategoryModel category;
    private final BooleanProperty validProperty = BooleanProperty.create(true);
    private final IntegerProperty listIndexProperty = IntegerProperty.create(-1);
    private final IntegerProperty listSizeProperty = IntegerProperty.create(-1);

    protected EntryModel(CategoryModel category) {
        this.category = category;
    }

    public CategoryModel getCategory() {
        return category;
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

    public int getListIndex() {
        return listIndexProperty().getValue();
    }

    public IntegerProperty listIndexProperty() {
        return listIndexProperty;
    }

    public void setListIndex(int value) {
        listIndexProperty().setValue(value);
    }

    public boolean isResetable() {
        return true;
    }

    public int getListSize() {
        return listSizeProperty().getValue();
    }

    public IntegerProperty listSizeProperty() {
        return listSizeProperty;
    }

    public void setListSize(int value) {
        listSizeProperty().setValue(value);
    }

    public abstract void apply();

    public void reset() {
    }

    public abstract Type getType();

    public enum Type {
        STRING, INTEGER, TEXT, ENUM, ACTION, ADD_LIST_ENTRY, BOOLEAN,
        ENCHANTMENT, HIDE_FLAG, ATTRIBUTE_MODIFIER, SELECTION, SELECTION_POTION, POTION_EFFECT, ARMOR_COLOR,
        VAULT_ITEM
    }
}
