package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry.item;

import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.ModTextures;
import com.github.franckyi.ibeeditor.client.editor.gui.standard.entry.IntegerEntryView;
import com.github.franckyi.ibeeditor.common.ModTexts;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class EnchantmentEntryView extends IntegerEntryView {
    private TexturedButton plusButton, minusButton;

    @Override
    public void build() {
        super.build();
        getRight().getChildren().add(1, vBox(2,
                plusButton = texturedButton(ModTextures.LEVEL_ADD, 11, 7, false)
                        .tooltip(ModTexts.LEVEL_ADD),
                minusButton = texturedButton(ModTextures.LEVEL_REMOVE, 11, 7, false)
                        .tooltip(ModTexts.LEVEL_REMOVE)
        ));
    }

    public TexturedButton getPlusButton() {
        return plusButton;
    }

    public TexturedButton getMinusButton() {
        return minusButton;
    }
}
