package com.github.franckyi.ibeeditor.base.client.mvc.model.entry;

import com.github.franckyi.gameadapter.api.common.text.Text;
import com.github.franckyi.ibeeditor.base.client.mvc.model.CategoryModel;

import java.util.function.Consumer;

public class ItemSelectionEntryModel extends StringEntryModel {
    public ItemSelectionEntryModel(CategoryModel category, Text label, String value, Consumer<String> action) {
        super(category, label, value, action);
    }

    @Override
    public Type getType() {
        return Type.ITEM_SELECTION;
    }
}
