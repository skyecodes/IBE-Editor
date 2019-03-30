package com.github.franckyi.ibeeditor.editor.item;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.editor.AbstractEditor;
import com.github.franckyi.ibeeditor.editor.property.TextProperty;
import com.github.franckyi.ibeeditor.network.ItemEditorMessage;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;

public class ItemEditor extends AbstractEditor {

    private ItemStack itemStack;

    public ItemEditor(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.build(
                category(
                        "Display",
                        new TextProperty("Name", itemStack.getDisplayName().getFormattedText(), s -> itemStack.setDisplayName(new TextComponentString(s)))
                ), category("Test")
        );
    }

    @Override
    public void apply() {
        super.apply();
        IBEEditorMod.CHANNEL.sendToServer(new ItemEditorMessage(itemStack));
    }
}
