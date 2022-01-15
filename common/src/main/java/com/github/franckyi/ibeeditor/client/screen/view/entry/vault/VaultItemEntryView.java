package com.github.franckyi.ibeeditor.client.screen.view.entry.vault;

import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.guapi.api.node.Label;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.ModTextures;
import com.github.franckyi.ibeeditor.client.screen.view.entry.EntryView;
import com.github.franckyi.ibeeditor.common.ModTexts;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class VaultItemEntryView extends EntryView {
    private ItemView itemView;
    private Label label;
    private TexturedButton openEditorButton, openNBTEditorButton, openSNBTEditorButton;

    @Override
    protected Node createContent() {
        return hBox(content -> {
            content.add(itemView = itemView());
            content.add(label = label(), 1);
            content.add(hBox(buttons -> {
                buttons.add(openEditorButton = texturedButton(ModTextures.EDITOR, 16, 16, false).tooltip(ModTexts.OPEN_EDITOR));
                buttons.add(openNBTEditorButton = texturedButton(ModTextures.NBT_EDITOR, 16, 16, false).tooltip(ModTexts.OPEN_NBT_EDITOR));
                buttons.add(openSNBTEditorButton = texturedButton(ModTextures.SNBT_EDITOR, 16, 16, false).tooltip(ModTexts.OPEN_SNBT_EDITOR));
                buttons.spacing(2);
            }));
            content.align(CENTER).spacing(5);
        });
    }

    public ItemView getItemView() {
        return itemView;
    }

    public Label getLabel() {
        return label;
    }

    public TexturedButton getOpenEditorButton() {
        return openEditorButton;
    }

    public TexturedButton getOpenNBTEditorButton() {
        return openNBTEditorButton;
    }

    public TexturedButton getOpenSNBTEditorButton() {
        return openSNBTEditorButton;
    }
}
