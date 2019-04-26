package com.github.franckyi.ibeeditor.client.editor.item;

import com.github.franckyi.ibeeditor.client.clipboard.logic.IBEClipboard;
import com.github.franckyi.ibeeditor.client.editor.Category;
import com.github.franckyi.ibeeditor.client.editor.property.ButtonProperty;
import com.github.franckyi.ibeeditor.client.util.ClientUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class ToolsItemCategory extends Category {

    private final ItemStack itemStack;

    public ToolsItemCategory(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.addAll(
                new ButtonProperty("Copy /give command", this::copyGiveCommand, TextFormatting.RED +
                        "Be careful !", "The text formatting won't be copied", "because it doesn't work with commands."),
                new ButtonProperty("Copy to IBE clipboard", this::copyToClipboard)
        );
    }

    private void copyToClipboard() {
        IBEClipboard.getInstance().addItem(itemStack.copy());
    }

    private void copyGiveCommand() {
        ClientUtils.copyGiveCommand(itemStack);
    }

}
