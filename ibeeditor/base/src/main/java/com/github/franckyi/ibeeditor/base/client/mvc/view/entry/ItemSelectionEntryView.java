package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TexturedButton;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class ItemSelectionEntryView extends StringEntryView {
    private TexturedButton itemListButton;

    @Override
    protected Node createLabeledContent() {
        return hBox(box -> {
            box.add(super.createLabeledContent(), 1);
            box.add(itemListButton = texturedButton("ibeeditor:textures/gui/search.png", 16, 16, false)
                    .tooltip(translated("ibeeditor.gui.choose_item")));
            box.align(CENTER).spacing(2);
        });
    }

    public TexturedButton getItemListButton() {
        return itemListButton;
    }
}
