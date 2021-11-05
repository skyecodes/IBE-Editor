package com.github.franckyi.ibeeditor.client.editor.gui.standard.entry;

import com.github.franckyi.guapi.api.Guapi;
import com.github.franckyi.ibeeditor.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.client.vault.VaultItemType;
import com.github.franckyi.ibeeditor.client.vault.item.ItemVaultItemModel;

public class InventoryItemEntryController extends EntryController<InventoryItemEntryModel, InventoryItemEntryView> {
    public InventoryItemEntryController(InventoryItemEntryModel model, InventoryItemEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getItemView().itemProperty().bind(model.itemProperty());
        model.slotProperty().addListener(this::updateSlotView);
        view.getSlotField().textProperty().addListener(newValue -> model.setSlot(Byte.parseByte(newValue)));
        view.getEditItemButton().onAction(this::openItemEditor);
        view.getOpenVaultButton().onAction(this::openVault);
        updateSlotView();
    }

    private void updateSlotView() {
        view.getSlotField().setText(Byte.toString(model.getSlot()));
    }

    private void openItemEditor() {
        ModScreenHandler.openItemEditorScreen(model.getItem(), value -> {
            model.setItem(value);
            Guapi.getScreenHandler().hideScene();
        }, null);
    }

    private void openVault() {
        ModScreenHandler.openVaultSelectionScreen(VaultItemType.ITEM, value -> {
            model.setItem(((ItemVaultItemModel) value).getItem());
            Guapi.getScreenHandler().hideScene();
        });
    }
}
