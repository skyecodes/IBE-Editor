package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry;

import com.github.franckyi.ibeeditor.client.editor.gui.standard.CategoryModel;
import com.github.franckyi.ibeeditor.client.util.selection.gui.list.ListSelectionItemModel;
import net.minecraft.network.chat.MutableComponent;

import java.util.List;
import java.util.function.Consumer;

public abstract class SelectionEntryModel extends StringEntryModel {
    public SelectionEntryModel(CategoryModel category, MutableComponent label, String value, Consumer<String> action) {
        super(category, label, value, action);
    }

    @Override
    public Type getType() {
        return Type.SELECTION;
    }

    public abstract List<String> getSuggestions();

    public abstract MutableComponent getSuggestionScreenTitle();

    public abstract List<? extends ListSelectionItemModel> getSelectionItems();
}
