package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.ibeeditor.base.client.ModTextures;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

import static com.github.franckyi.guapi.GuapiHelper.texturedButton;
import static com.github.franckyi.guapi.GuapiHelper.vBox;

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
