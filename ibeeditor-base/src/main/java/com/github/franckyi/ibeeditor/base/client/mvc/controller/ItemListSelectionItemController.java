package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemListSelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ItemListSelectionItemView;

public class ItemListSelectionItemController<M extends ItemListSelectionItemModel, V extends ItemListSelectionItemView> extends ListSelectionItemController<M, V> {
    public ItemListSelectionItemController(M model, V view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getItemView().setItem(model.getItem());
    }
}
