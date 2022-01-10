package com.github.franckyi.ibeeditor.client.screen.controller.selection.color;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.ibeeditor.client.screen.model.selection.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.client.screen.view.selection.color.PotionColorSelectionScreenView;
import com.github.franckyi.ibeeditor.common.ColoredItemHelper;
import net.minecraft.resources.ResourceLocation;

public class PotionColorSelectionScreenController extends ColorSelectionScreenController<PotionColorSelectionScreenView> {
    public PotionColorSelectionScreenController(ColorSelectionScreenModel model, PotionColorSelectionScreenView view) {
        super(model, view);
    }

    @Override
    protected void updateExample() {
        super.updateExample();
        view.getExamplePotion().setItem(ColoredItemHelper.createColoredPotionItem(new ResourceLocation("empty"),
                Color.fromRGB((int) model.getRedValue(), (int) model.getGreenValue(), (int) model.getBlueValue())));
    }
}
