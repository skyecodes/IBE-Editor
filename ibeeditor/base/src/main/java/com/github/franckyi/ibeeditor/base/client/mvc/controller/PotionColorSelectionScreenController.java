package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.gameadapter.Color;
import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.PotionColorSelectionScreenView;

public class PotionColorSelectionScreenController extends ColorSelectionScreenController<PotionColorSelectionScreenView> {
    public PotionColorSelectionScreenController(ColorSelectionScreenModel model, PotionColorSelectionScreenView view) {
        super(model, view);
    }

    @Override
    protected void updateExample() {
        super.updateExample();
        view.getExamplePotion().setItem(Game.getCommon().createPotionItem("minecraft:empty",
                Color.fromRGB((int) model.getRedValue(), (int) model.getGreenValue(), (int) model.getBlueValue())));
    }
}
