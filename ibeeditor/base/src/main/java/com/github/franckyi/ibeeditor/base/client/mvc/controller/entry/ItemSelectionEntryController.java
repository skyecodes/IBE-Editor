package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.ibeeditor.base.client.ClientCache;
import com.github.franckyi.ibeeditor.base.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.ItemSelectionEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.ItemSelectionEntryView;

public class ItemSelectionEntryController extends StringEntryController<ItemSelectionEntryModel, ItemSelectionEntryView> {
    public ItemSelectionEntryController(ItemSelectionEntryModel model, ItemSelectionEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getTextField().getSuggestions().setAll(ClientCache.getItemSuggestions());
        view.getItemListButton().onAction(this::openItemList);
    }

    private void openItemList() {
        ModScreenHandler.openSelectionScreen("ibeeditor.gui.choose_item",
                model.getValue().contains(":") ? model.getValue() : "minecraft:" + model.getValue(),
                ClientCache.getItemSelectionItems(), model::setValue);
    }
}
