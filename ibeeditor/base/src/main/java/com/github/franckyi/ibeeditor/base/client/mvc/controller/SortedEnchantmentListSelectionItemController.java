package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.ibeeditor.base.client.mvc.model.SortedEnchantmentListSelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ItemListSelectionItemView;

public class SortedEnchantmentListSelectionItemController extends ItemListSelectionItemController<SortedEnchantmentListSelectionItemModel, ItemListSelectionItemView> {
    public SortedEnchantmentListSelectionItemController(SortedEnchantmentListSelectionItemModel model, ItemListSelectionItemView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        if (model.isCurse()) {
            view.getNameLabel().getLabel().red();
        } else if (model.canApply()) {
            view.getNameLabel().getLabel().green();
        }
    }
}
