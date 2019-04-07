package com.github.franckyi.ibeeditor.editor.item.category;

import com.github.franckyi.ibeeditor.editor.PropertyList;
import net.minecraft.item.ItemStack;

public abstract class ItemEditorPropertyList extends PropertyList {

    protected ItemStack itemStack;

    public ItemEditorPropertyList(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

}
