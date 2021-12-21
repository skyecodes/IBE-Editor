package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.guapi.api.node.builder.HBoxBuilder;
import com.github.franckyi.ibeeditor.base.client.ModTextures;
import com.github.franckyi.ibeeditor.base.common.ModTexts;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class EnchantmentEntryView extends SelectionEntryView {
    private TextField levelField;
    private TexturedButton plusButton, minusButton;

    @Override
    protected Node createLabeledContent() {
        HBoxBuilder box = (HBoxBuilder) super.createLabeledContent();
        box.add(levelField = textField().prefSize(30, 16).tooltip(ModTexts.LEVEL));
        box.add(vBox(2,
                plusButton = texturedButton(ModTextures.LEVEL_ADD, 11, 7, false)
                        .tooltip(ModTexts.LEVEL_ADD),
                minusButton = texturedButton(ModTextures.LEVEL_REMOVE, 11, 7, false)
                        .tooltip(ModTexts.LEVEL_REMOVE)
        ));
        return box;
    }

    public TextField getLevelField() {
        return levelField;
    }

    public TexturedButton getPlusButton() {
        return plusButton;
    }

    public TexturedButton getMinusButton() {
        return minusButton;
    }
}
