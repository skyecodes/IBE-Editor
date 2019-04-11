package com.github.franckyi.ibeeditor.editor.item;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.editor.AbstractEditor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTippedArrow;

import java.util.function.Function;

public class ItemEditor extends AbstractEditor {

    private final ItemStack itemStack;
    private final Function<ItemStack, ?> action;

    public ItemEditor(ItemStack itemStack, Function<ItemStack, ?> action) {
        super();
        this.itemStack = itemStack;
        this.action = action;
        this.addCategory("General", new GeneralCategory(itemStack));
        this.addCategory("Enchantments", new EnchantmentsCategory(itemStack));
        this.addCategory("Attribute modifiers", new AttributeModifiersCategory(itemStack));
        if (itemStack.getItem() instanceof ItemPotion || itemStack.getItem() instanceof ItemTippedArrow) {
            this.addCategory("Potion effects");
        }
        this.addCategory("Hide Flags", new HideFlagsCategory(itemStack));
        this.addCategory("Can destroy", new BlockCategory(itemStack, "CanDestroy"));
        if (itemStack.getItem() instanceof ItemBlock) {
            this.addCategory("Can place on", new BlockCategory(itemStack, "CanPlaceOn"));
        }
        this.show();
    }

    @Override
    protected void apply() {
        super.apply();
        IBEEditorMod.CHANNEL.sendToServer(action.apply(itemStack));
    }

}
