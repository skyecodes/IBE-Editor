package com.github.franckyi.ibeeditor.client.screen.view.selection.color;

import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.guapi.api.node.Node;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class PotionColorSelectionScreenView extends ColorSelectionScreenView {
    private ItemView examplePotion;

    @Override
    protected Node createExample() {
        return examplePotion = itemView();
    }

    public ItemView getExamplePotion() {
        return examplePotion;
    }
}
