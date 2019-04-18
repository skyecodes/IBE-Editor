package com.github.franckyi.ibeeditor.client.item;

import com.github.franckyi.ibeeditor.IBEUtils;
import com.github.franckyi.ibeeditor.client.Category;
import com.github.franckyi.ibeeditor.client.clipboard.Clipboard;
import com.github.franckyi.ibeeditor.client.property.ButtonProperty;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class ToolsItemCategory extends Category {

    private ItemStack itemStack;

    public ToolsItemCategory(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.addAll(
                new ButtonProperty("Copy /give command", this::copyGiveCommand, TextFormatting.RED +
                        "Be careful !", "The text formatting won't be copied", "because it doesn't work with commands."),
                new ButtonProperty("Copy to IBE clipboard", this::copyToClipboard)
        );
    }

    private void copyToClipboard() {
        Clipboard.getInstance().getClipboardItems().add(itemStack.copy());
    }

    private void copyGiveCommand() {
        mc.keyboardListener.setClipboardString(IBEUtils.unformat("/give @p "
                + itemStack.getItem().getRegistryName()
                + itemStack.getOrCreateTag()) + " "
                + itemStack.getCount());
    }

}
