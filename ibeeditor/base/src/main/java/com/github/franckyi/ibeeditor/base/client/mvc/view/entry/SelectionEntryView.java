package com.github.franckyi.ibeeditor.base.client.mvc.view.entry;

import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.ibeeditor.base.client.ModTextures;

import static com.github.franckyi.guapi.GuapiHelper.*;

public class SelectionEntryView extends StringEntryView {
    private TexturedButton itemListButton;

    @Override
    protected Node createLabeledContent() {
        return hBox(box -> {
            box.add(super.createLabeledContent(), 1);
            box.add(itemListButton = texturedButton(ModTextures.SEARCH, 16, 16, false));
            box.align(CENTER).spacing(2);
        });
    }

    public TexturedButton getItemListButton() {
        return itemListButton;
    }
}
