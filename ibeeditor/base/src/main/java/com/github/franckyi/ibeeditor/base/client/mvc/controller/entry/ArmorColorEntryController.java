package com.github.franckyi.ibeeditor.base.client.mvc.controller.entry;

import com.github.franckyi.gameadapter.Color;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.ibeeditor.base.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.entry.ArmorColorEntryModel;
import com.github.franckyi.ibeeditor.base.client.mvc.view.entry.ArmorColorEntryView;

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
        itemView.setItem(IItemStack.fromArmor(itemView.getItem().getData(), color));
    }
}
