package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

import com.github.franckyi.guapi.api.node.Button;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class EnchantmentEntryView extends IntegerEntryView {
    private Button plusButton, minusButton;

    @Override
    public void build() {
        super.build();
        getRight().getChildren().add(1, vBox(2,
                plusButton = button(text("+").green()).tooltip(translated("ibeeditor.gui.level_add").with(text("+1")).green()).prefSize(16, 7),
                minusButton = button(text("-").red()).tooltip(translated("ibeeditor.gui.level_add").with(text("-1")).red()).prefSize(16, 7)
        ));
        getRoot().setWeight(getLabel(), 2);
    }

    public Button getPlusButton() {
        return plusButton;
    }

    public Button getMinusButton() {
        return minusButton;
    }
}
