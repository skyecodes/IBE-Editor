package com.github.franckyi.ibeeditor.client.util.selection.gui.color;

import com.github.franckyi.guapi.api.node.Node;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class TextColorSelectionScreenView extends ColorSelectionScreenView {
    private MutableComponent exampleText;

    @Override
    protected Node createExample() {
        exampleText = text("Test ").append(text("Test").withStyle(ChatFormatting.BOLD)).append(text(" Test").withStyle(ChatFormatting.ITALIC));
        return label(exampleText).tooltip(exampleText).textAlign(CENTER);
    }

    public MutableComponent getExampleText() {
        return exampleText;
    }
}
