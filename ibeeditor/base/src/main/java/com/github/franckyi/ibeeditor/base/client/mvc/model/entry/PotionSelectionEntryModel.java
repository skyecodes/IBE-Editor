package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.base.client.ClientCache;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.SelectionItemModel;

import java.util.List;
import java.util.function.Consumer;

public class PotionSelectionEntryModel extends SelectionEntryModel {
    public PotionSelectionEntryModel(CategoryModel category, Text label, String value, Consumer<String> action) {
        super(category, label, value, action);
    }

    @Override
    public List<String> getSuggestions() {
        return ClientCache.getPotionSuggestions();
    }

    @Override
    public String getSuggestionScreenTitle() {
        return null;
    }

    @Override
    public List<? extends SelectionItemModel> getSelectionItems() {
        return null;
    }
}
