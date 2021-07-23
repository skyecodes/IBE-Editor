package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.ItemEnchantmentEditorEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.ItemEnchantmentEditorEntryView;

public class ItemEnchantmentEditorEntryController extends IntegerEditorEntryController<ItemEnchantmentEditorEntryModel, ItemEnchantmentEditorEntryView> {
    public ItemEnchantmentEditorEntryController(ItemEnchantmentEditorEntryModel model, ItemEnchantmentEditorEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getPlusButton().onAction(() -> model.setValue(model.getValue() + 1));
        view.getMinusButton().onAction(() -> model.setValue(model.getValue() - 1));
    }
}
