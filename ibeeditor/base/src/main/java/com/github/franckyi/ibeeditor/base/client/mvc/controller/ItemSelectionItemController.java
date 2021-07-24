package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemSelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ItemSelectionItemView;

public class ItemSelectionItemController extends SelectionItemController<ItemSelectionItemModel, ItemSelectionItemView> {
    public ItemSelectionItemController(ItemSelectionItemModel model, ItemSelectionItemView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getItemView().setItem(model.getItem());
    }
}
