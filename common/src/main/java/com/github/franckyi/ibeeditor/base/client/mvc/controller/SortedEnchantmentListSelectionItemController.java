package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.ibeeditor.base.client.mvc.model.SortedEnchantmentListSelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ItemListSelectionItemView;
import net.minecraft.ChatFormatting;

public class SortedEnchantmentListSelectionItemController extends ItemListSelectionItemController<SortedEnchantmentListSelectionItemModel, ItemListSelectionItemView> {
    public SortedEnchantmentListSelectionItemController(SortedEnchantmentListSelectionItemModel model, ItemListSelectionItemView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        if (model.isCurse()) {
            view.getNameLabel().setLabel(view.getNameLabel().getLabel().copy().withStyle(ChatFormatting.RED));
        } else if (model.canApply()) {
            view.getNameLabel().setLabel(view.getNameLabel().getLabel().copy().withStyle(ChatFormatting.GREEN));
        }
    }
}
