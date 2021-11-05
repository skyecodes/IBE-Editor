package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry;

import com.github.franckyi.ibeeditor.client.editor.gui.standard.CategoryModel;
import com.github.franckyi.ibeeditor.client.util.selection.ListSelectionItemCache;
import com.github.franckyi.ibeeditor.client.util.selection.gui.list.ListSelectionItemModel;
import com.github.franckyi.ibeeditor.common.ModTexts;
import net.minecraft.network.chat.MutableComponent;

import java.util.List;
import java.util.function.Consumer;

public class ItemSelectionEntryModel extends SelectionEntryModel {
    public ItemSelectionEntryModel(CategoryModel category, MutableComponent label, String value, Consumer<String> action) {
        super(category, label, value, action);
    }

    @Override
    public List<String> getSuggestions() {
        return ListSelectionItemCache.getItemSuggestions();
    }

    @Override
    public MutableComponent getSuggestionScreenTitle() {
        return ModTexts.ITEM;
    }

    @Override
    public List<? extends ListSelectionItemModel> getSelectionItems() {
        return ListSelectionItemCache.getItemSelectionItems();
    }
}
