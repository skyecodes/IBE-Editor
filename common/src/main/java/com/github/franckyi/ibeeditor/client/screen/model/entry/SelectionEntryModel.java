package com.github.franckyi.ibeeditor.client.screen.model.entry;

import com.github.franckyi.ibeeditor.client.screen.model.category.CategoryModel;
import com.github.franckyi.ibeeditor.client.screen.model.selection.element.ListSelectionElementModel;
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

    public abstract MutableComponent getSelectionScreenTitle();

    public abstract List<? extends ListSelectionElementModel> getSelectionItems();
}
