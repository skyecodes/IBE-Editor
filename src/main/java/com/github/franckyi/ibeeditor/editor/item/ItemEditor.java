package com.github.franckyi.ibeeditor.editor.item;

import com.github.franckyi.ibeeditor.editor.AbstractEditor;
import com.github.franckyi.ibeeditor.editor.EditorCategory;
import com.github.franckyi.ibeeditor.editor.property.TextProperty;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;

public class ItemEditor extends AbstractEditor {

    private ItemStack itemStack;
    private EditorCategory display;

    public ItemEditor(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.build(
                category(
                        "Display",
                        new TextProperty("Name", itemStack.getDisplayName().getFormattedText(), s -> itemStack.setDisplayName(new TextComponentString(s)))
                ), category("Test")
        );
    }

}
