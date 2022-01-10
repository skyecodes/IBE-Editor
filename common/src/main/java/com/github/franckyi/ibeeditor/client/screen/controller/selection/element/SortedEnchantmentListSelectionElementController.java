package com.github.franckyi.ibeeditor.client.screen.controller.selection.element;

import com.github.franckyi.ibeeditor.client.screen.model.selection.element.SortedEnchantmentListSelectionElementModel;
import com.github.franckyi.ibeeditor.client.screen.view.selection.element.ItemListSelectionElementView;
import net.minecraft.ChatFormatting;

public class SortedEnchantmentListSelectionElementController extends ItemListSelectionElementController<SortedEnchantmentListSelectionElementModel, ItemListSelectionElementView> {
    public SortedEnchantmentListSelectionElementController(SortedEnchantmentListSelectionElementModel model, ItemListSelectionElementView view) {
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
