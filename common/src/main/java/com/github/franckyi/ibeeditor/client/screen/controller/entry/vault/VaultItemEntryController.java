package com.github.franckyi.ibeeditor.client.screen.controller.entry.vault;

import com.github.franckyi.ibeeditor.client.ModScreenHandler;
import com.github.franckyi.ibeeditor.client.screen.controller.entry.EntryController;
import com.github.franckyi.ibeeditor.client.screen.model.entry.vault.VaultItemEntryModel;
import com.github.franckyi.ibeeditor.client.screen.view.entry.vault.VaultItemEntryView;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class VaultItemEntryController extends EntryController<VaultItemEntryModel, VaultItemEntryView> {
    public VaultItemEntryController(VaultItemEntryModel model, VaultItemEntryView view) {
        super(model, view);
    }

    @Override
    public void bind() {
        super.bind();
        view.getItemView().itemProperty().bind(model.itemStackProperty());
        view.getLabel().labelProperty().bind(model.itemStackProperty().map(ItemStack::getHoverName));
        view.getButtonBox().getChildren().remove(view.getResetButton());
        view.getOpenEditorButton().onAction(() -> ModScreenHandler.openItemEditorScreen(model.getItemStack(), model::setItemStack, null));
        view.getOpenNBTEditorButton().onAction(() -> ModScreenHandler.openNBTEditorScreen(model.getItemStack().save(new CompoundTag()), tag -> model.setItemStack(ItemStack.of(tag)), null, true));
        view.getOpenSNBTEditorButton().onAction(() -> ModScreenHandler.openSNBTEditorScreen(model.getItemStack().save(new CompoundTag()), tag -> model.setItemStack(ItemStack.of(tag)), null, true));
    }
}
