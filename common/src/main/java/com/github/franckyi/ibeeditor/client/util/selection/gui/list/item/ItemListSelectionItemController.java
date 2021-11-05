package com.github.franckyi.ibeeditor.client.util.selection.gui.list.item;

import com.github.franckyi.ibeeditor.client.util.selection.gui.list.ListSelectionItemController;

public class ItemListSelectionItemController extends ListSelectionItemController<ItemListSelectionItemModel, ItemListSelectionItemView> {
    public ItemListSelectionItemController(ItemListSelectionItemModel model, ItemListSelectionItemView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getItemView().setItem(model.getItem());
    }
}
