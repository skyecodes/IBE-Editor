package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemListSelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ItemListSelectionItemView;

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
