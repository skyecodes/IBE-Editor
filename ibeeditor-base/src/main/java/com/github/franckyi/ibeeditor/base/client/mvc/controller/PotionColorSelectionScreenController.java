package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.gameadapter.api.Color;
import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.PotionColorSelectionScreenView;

public class PotionColorSelectionScreenController extends ColorSelectionScreenController<PotionColorSelectionScreenView> {
    public PotionColorSelectionScreenController(ColorSelectionScreenModel model, PotionColorSelectionScreenView view) {
        super(model, view);
    }

    @Override
    protected void updateExample() {
        super.updateExample();
        view.getExamplePotion().setItem(IItemStack.fromPotion(IIdentifier.of("minecraft:empty"),
                Color.fromRGB((int) model.getRedValue(), (int) model.getGreenValue(), (int) model.getBlueValue())));
    }
}
