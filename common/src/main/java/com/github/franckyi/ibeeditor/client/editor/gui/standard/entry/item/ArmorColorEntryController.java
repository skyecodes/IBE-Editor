package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry.item;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.ibeeditor.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.entry.LabeledEntryController;
import com.github.franckyi.ibeeditor.client.util.selection.gui.color.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.common.TagHelper;
import net.minecraft.nbt.CompoundTag;

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
        itemView.setItem(TagHelper.createArmorItemWithColor(itemView.getItem().save(new CompoundTag()), color));
    }
}
