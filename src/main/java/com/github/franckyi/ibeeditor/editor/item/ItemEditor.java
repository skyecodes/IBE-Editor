package com.github.franckyi.ibeeditor.editor.item;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.editor.AbstractEditor;
import com.github.franckyi.ibeeditor.editor.item.category.DisplayPropertyList;
import com.github.franckyi.ibeeditor.editor.item.category.EnchantmentsPropertyList;
import com.github.franckyi.ibeeditor.editor.item.category.HideFlagsPropertyList;
import com.github.franckyi.ibeeditor.network.ItemEditorMessage;
import net.minecraft.item.ItemStack;

public class ItemEditor extends AbstractEditor {

    private ItemStack itemStack;

    public ItemEditor(ItemStack itemStack) {
        super();
        this.itemStack = itemStack;
        this.addCategory("Display", new DisplayPropertyList(itemStack));
        this.addCategory("Hide Flags", new HideFlagsPropertyList(itemStack));
        this.addCategory("Enchantments", new EnchantmentsPropertyList(itemStack));
        this.show();
    }

    @Override
    protected void apply() {
        super.apply();
        IBEEditorMod.CHANNEL.sendToServer(new ItemEditorMessage(itemStack));
    }

}
