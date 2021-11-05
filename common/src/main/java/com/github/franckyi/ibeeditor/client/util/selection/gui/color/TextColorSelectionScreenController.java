package com.github.franckyi.ibeeditor.client.util.selection.gui.color;

import net.minecraft.network.chat.TextColor;

public class TextColorSelectionScreenController extends ColorSelectionScreenController<TextColorSelectionScreenView> {
    public TextColorSelectionScreenController(ColorSelectionScreenModel model, TextColorSelectionScreenView view) {
        super(model, view);
    }

    @Override
    protected void updateExample() {
        super.updateExample();
        view.getExampleText().withStyle(style -> style.withColor(TextColor.parseColor(model.getHexValue())));
    }
}
