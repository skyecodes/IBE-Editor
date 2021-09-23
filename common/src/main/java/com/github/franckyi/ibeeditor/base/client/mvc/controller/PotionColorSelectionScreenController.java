package com.github.franckyi.ibeeditor.base.client.mvc.controller;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.PotionColorSelectionScreenView;
import com.github.franckyi.ibeeditor.base.common.TagHelper;
import net.minecraft.resources.ResourceLocation;

public class PotionColorSelectionScreenController extends ColorSelectionScreenController<PotionColorSelectionScreenView> {
    public PotionColorSelectionScreenController(ColorSelectionScreenModel model, PotionColorSelectionScreenView view) {
        super(model, view);
    }

    @Override
    protected void updateExample() {
        super.updateExample();
        view.getExamplePotion().setItem(TagHelper.fromPotion(new ResourceLocation("empty"),
                Color.fromRGB((int) model.getRedValue(), (int) model.getGreenValue(), (int) model.getBlueValue())));
    }
}
