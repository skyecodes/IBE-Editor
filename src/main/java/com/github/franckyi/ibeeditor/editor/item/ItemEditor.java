package com.github.franckyi.ibeeditor.editor.item;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.editor.AbstractEditor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTippedArrow;

import java.util.function.Function;

public class ItemEditor extends AbstractEditor {

    private ItemStack itemStack;
    private Function<ItemStack, ?> action;

    public ItemEditor(ItemStack itemStack, Function<ItemStack, ?> action) {
        super();
        this.itemStack = itemStack;
        this.action = action;
        this.addCategory("General", new GeneralPropertyList(itemStack));
        this.addCategory("Enchantments", new EnchantmentsPropertyList(itemStack));
        this.addCategory("Attribute modifiers", new AttributeModifiersPropertyList(itemStack));
        this.addCategory("Hide Flags", new HideFlagsPropertyList(itemStack));
        this.addCategory("Can destroy", new BlockPropertyList(itemStack, "CanDestroy"));
        if (itemStack.getItem() instanceof ItemBlock) {
            this.addCategory("Can place on", new BlockPropertyList(itemStack, "CanPlaceOn"));
        }
        if (itemStack.getItem() instanceof ItemPotion || itemStack.getItem() instanceof ItemTippedArrow) {
            this.addCategory("Potion effects");
        }
        this.show();
    }

    @Override
    protected void apply() {
        super.apply();
        IBEEditorMod.CHANNEL.sendToServer(action.apply(itemStack));
    }

}
