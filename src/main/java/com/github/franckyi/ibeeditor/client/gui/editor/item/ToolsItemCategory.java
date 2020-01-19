package com.github.franckyi.ibeeditor.client.gui.editor.item;

import com.github.franckyi.ibeeditor.client.ClientUtils;
import com.github.franckyi.ibeeditor.client.clipboard.IBEClipboard;
import com.github.franckyi.ibeeditor.client.gui.editor.base.category.ToolsCategory;
import net.minecraft.item.ItemStack;

public class ToolsItemCategory extends ToolsCategory {

    private final ItemStack itemStack;

    public ToolsItemCategory(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.addCommand("give", this::copyGiveCommand, this::copyGiveCommandWithoutFormatting);
        this.addSimple("Copy to IBE clipboard", this::copyToClipboard);
    }

    private void copyToClipboard() {
        IBEClipboard.getInstance().addItem(itemStack.copy());
    }

    private void copyGiveCommand() {
        ClientUtils.copyGiveCommand(itemStack);
    }

    private void copyGiveCommandWithoutFormatting() {
        ClientUtils.copyGiveCommandWithoutFormatting(itemStack);
    }

}
