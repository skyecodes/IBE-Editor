package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.databindings.api.IntegerProperty;
import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.client.ClientCache;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ListSelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.category.ItemCategoryModel;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

import java.util.List;
import java.util.function.BiConsumer;

public class EnchantmentEntryModel extends SelectionEntryModel {
    private final IntegerProperty levelProperty;
    private final BiConsumer<String, Integer> action;
    protected int defaultLevel;

    public EnchantmentEntryModel(CategoryModel category, String id, int level, BiConsumer<String, Integer> action) {
        super(category, null, id, null);
        levelProperty = IntegerProperty.create(level);
        this.action = action;
        defaultLevel = level;
    }

    public int getLevel() {
        return levelProperty().getValue();
    }

    public IntegerProperty levelProperty() {
        return levelProperty;
    }

    public void setLevel(int value) {
        levelProperty().setValue(value);
    }

    @Override
    public void apply() {
        action.accept(getValue(), getLevel());
        defaultValue = getValue();
        defaultLevel = getLevel();
    }

    @Override
    public void reset() {
        super.reset();
        setLevel(defaultLevel);
    }

    @Override
    public ItemCategoryModel getCategory() {
        return (ItemCategoryModel) super.getCategory();
    }

    @Override
    public Type getType() {
        return Type.ENCHANTMENT;
    }

    @Override
    public List<String> getSuggestions() {
        return ClientCache.getEnchantmentSuggestions();
    }

    @Override
    public IText getSuggestionScreenTitle() {
        return ModTexts.ENCHANTMENT;
    }

    @Override
    public List<? extends ListSelectionItemModel> getSelectionItems() {
        return ClientCache.getSortedEnchantmentSelectionItems(getCategory().getEditor().getTarget());
    }
}
