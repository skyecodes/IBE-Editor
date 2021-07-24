package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

import com.github.franckyi.guapi.api.node.TexturedButton;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class EnchantmentEntryView extends IntegerEntryView {
    private TexturedButton plusButton, minusButton;

    @Override
    public void build() {
        super.build();
        getRight().getChildren().add(1, vBox(2,
                plusButton = texturedButton("ibeeditor:textures/gui/level_add.png", 11, 7, false)
                        .tooltip(translated("ibeeditor.gui.level_add").with(text("+1")).green()),
                minusButton = texturedButton("ibeeditor:textures/gui/level_remove.png", 11, 7, false)
                        .tooltip(translated("ibeeditor.gui.level_add").with(text("-1")).red())
        ));
        getRoot().setWeight(getLabel(), 2);
    }

    public TexturedButton getPlusButton() {
        return plusButton;
    }

    public TexturedButton getMinusButton() {
        return minusButton;
    }
}
