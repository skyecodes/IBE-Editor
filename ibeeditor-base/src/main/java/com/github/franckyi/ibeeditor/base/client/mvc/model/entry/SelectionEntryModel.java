package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.gameadapter.api.common.text.IText;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ListSelectionItemModel;

import java.util.List;
import java.util.function.Consumer;

public abstract class SelectionEntryModel extends StringEntryModel {
    public SelectionEntryModel(CategoryModel category, IText label, String value, Consumer<String> action) {
        super(category, label, value, action);
    }

    @Override
    public Type getType() {
        return Type.SELECTION;
    }

    public abstract List<String> getSuggestions();

    public abstract IText getSuggestionScreenTitle();

    public abstract List<? extends ListSelectionItemModel> getSelectionItems();
}
