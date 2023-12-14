package com.github.franckyi.ibeeditor.client.screen.controller.selection.color;

import com.github.franckyi.ibeeditor.client.screen.model.selection.ColorSelectionScreenModel;
import com.github.franckyi.ibeeditor.client.screen.view.selection.color.TextColorSelectionScreenView;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextColor;

import static com.github.franckyi.guapi.api.GuapiHelper.text;

public class TextColorSelectionScreenController extends ColorSelectionScreenController<TextColorSelectionScreenView> {
    public TextColorSelectionScreenController(ColorSelectionScreenModel model, TextColorSelectionScreenView view) {
        super(model, view);
    }

    @Override
    protected void updateExample() {
        super.updateExample();
        var text = text("Test ")
                .append(text("Test").withStyle(ChatFormatting.BOLD))
                .append(text(" Test").withStyle(ChatFormatting.ITALIC));
        TextColor.parseColor(model.getHexValue()).result().ifPresent(color -> text.withStyle(style -> style.withColor(color)));
        view.getExampleLabel().setLabel(text);
        view.getExampleLabel().getTooltip().setAll(text);
    }
}
