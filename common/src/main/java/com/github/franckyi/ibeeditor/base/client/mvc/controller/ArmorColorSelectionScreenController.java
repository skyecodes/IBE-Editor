package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.ArmorColorSelectionScreenView;
import com.github.franckyi.ibeeditor.base.common.TagHelper;
import net.minecraft.nbt.CompoundTag;

public class ArmorColorSelectionScreenController extends ColorSelectionScreenController<ArmorColorSelectionScreenView> {
    public ArmorColorSelectionScreenController(ColorSelectionScreenModel model, ArmorColorSelectionScreenView view) {
        super(model, view);
    }

    @Override
    protected void updateExample() {
        super.updateExample();
        view.getExampleItems().forEach(itemView -> updateItemColor(itemView, Color.fromRGB(
                (int) model.getRedValue(), (int) model.getGreenValue(), (int) model.getBlueValue())));
    }

    private void updateItemColor(ItemView itemView, int color) {
        itemView.setItem(TagHelper.fromArmor(itemView.getItem().save(new CompoundTag()), color));
    }
}
