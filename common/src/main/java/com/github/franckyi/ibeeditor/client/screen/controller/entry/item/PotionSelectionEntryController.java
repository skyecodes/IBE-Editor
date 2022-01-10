package com.github.franckyi.ibeeditor.client.screen.controller.entry.item;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.ibeeditor.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.client.screen.controller.entry.SelectionEntryController;
import com.github.franckyi.ibeeditor.client.screen.model.entry.item.PotionSelectionEntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.selection.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.client.screen.view.entry.item.PotionSelectionEntryView;
import com.github.franckyi.ibeeditor.common.ColoredItemHelper;
import net.minecraft.resources.ResourceLocation;

public class PotionSelectionEntryController extends SelectionEntryController<PotionSelectionEntryModel, PotionSelectionEntryView> {
    public PotionSelectionEntryController(PotionSelectionEntryModel model, PotionSelectionEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getChooseColorButton().onAction(() -> ModScreenHandler.openColorSelectionScreen(ColorSelectionScreenModel.Target.POTION, model.getCustomColor(), this::updatePotionColor));
        view.getRemoveColorButton().onAction(() -> model.setCustomColor(Color.NONE));
        view.getResetColorButton().onAction(model::resetCustomColor);
        model.customColorProperty().addListener(this::updatePotionItem);
        model.valueProperty().addListener(this::updatePotionItem);
        updatePotionItem();
    }

    private void updatePotionItem() {
        view.getPotionView().setItem(ColoredItemHelper.createColoredPotionItem(ResourceLocation.tryParse(model.getValue()), model.getCustomColor()));
    }

    private void updatePotionColor(String value) {
        model.setCustomColor(Color.fromHex(value));
    }
}
