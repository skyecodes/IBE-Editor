package com.github.franckyi.ibeeditor.client.screen.controller.selection.color;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.ibeeditor.client.screen.model.selection.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.client.screen.view.selection.color.ArmorColorSelectionScreenView;
import com.github.franckyi.ibeeditor.common.ColoredItemHelper;

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
        itemView.setItem(ColoredItemHelper.createColoredArmorItem(itemView.getItem(), color));
    }
}
