package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry;

import com.github.franckyi.guapi.api.node.Button;
import com.github.franckyi.guapi.api.node.ItemView;
import com.github.franckyi.guapi.api.node.Node;
import com.github.franckyi.guapi.api.node.TextField;
import com.github.franckyi.guapi.api.util.Align;
import com.github.franckyi.guapi.api.util.Predicates;
import com.github.franckyi.ibeeditor.common.ModTexts;

import static com.github.franckyi.guapi.api.GuapiHelper.*;

public class InventoryItemEntryView extends EntryView {
    private ItemView itemView;
    private TextField slotField;
    private Button editItemButton;
    private Button openVaultButton;

    @Override
    protected Node createContent() {
        return hBox(root -> {
            root.add(itemView = itemView().renderDecorations());
            root.add(label(ModTexts.SLOT));
            root.add(slotField = textField().validator(Predicates.IS_BYTE).maxWidth(40));
            root.add(editItemButton = button(ModTexts.edit(ModTexts.ITEM)));
            root.add(openVaultButton = button(ModTexts.OPEN_VAULT));
            root.align(Align.CENTER_LEFT).spacing(5);
        });
    }

    public ItemView getItemView() {
        return itemView;
    }

    public TextField getSlotField() {
        return slotField;
    }

    public Button getEditItemButton() {
        return editItemButton;
    }

    public Button getOpenVaultButton() {
        return openVaultButton;
    }
}
