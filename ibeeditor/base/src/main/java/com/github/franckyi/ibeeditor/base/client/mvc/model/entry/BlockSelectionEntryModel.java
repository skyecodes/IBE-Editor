package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.client.ClientCache;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ListSelectionItemModel;

import java.util.List;
import java.util.function.Consumer;

public class BlockSelectionEntryModel extends SelectionEntryModel {
    public BlockSelectionEntryModel(CategoryModel category, IText label, String value, Consumer<String> action) {
        super(category, label, value, action);
    }

    @Override
    public List<String> getSuggestions() {
        return ClientCache.getBlockSuggestions();
    }

    @Override
    public String getSuggestionScreenTitle() {
        return "ibeeditor.text.block";
    }

    @Override
    public List<? extends ListSelectionItemModel> getSelectionItems() {
        return ClientCache.getBlockSelectionItems();
    }
}
