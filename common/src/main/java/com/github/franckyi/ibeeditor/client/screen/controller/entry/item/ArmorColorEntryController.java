package com.github.franckyi.ibeeditor.client.screen.controller.entry.item;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.ibeeditor.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.client.screen.controller.entry.LabeledEntryController;
import com.github.franckyi.ibeeditor.client.screen.model.entry.item.ArmorColorEntryModel;
import com.github.franckyi.ibeeditor.client.screen.model.selection.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.client.screen.view.entry.item.ArmorColorEntryView;
import com.github.franckyi.ibeeditor.common.ColoredItemHelper;

public class ArmorColorEntryController extends LabeledEntryController<ArmorColorEntryModel, ArmorColorEntryView> {
    public ArmorColorEntryController(ArmorColorEntryModel model, ArmorColorEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getChooseColorButton().onAction(() -> ModScreenHandler.openColorSelectionScreen(ColorSelectionScreenModel.Target.LEATHER_ARMOR, model.getValue(), hex -> model.setValue(Color.fromHex(hex))));
        view.getRemoveColorButton().onAction(() -> model.setValue(Color.NONE));
        model.valueProperty().addListener(this::updateColor);
        updateColor();
    }

    private void updateColor() {
        view.getItemViews().forEach(itemView -> updateItemColor(itemView, model.getValue()));
    }

    private void updateItemColor(ItemView itemView, int color) {
        itemView.setItem(ColoredItemHelper.createColoredArmorItem(itemView.getItem(), color));
    }
}
