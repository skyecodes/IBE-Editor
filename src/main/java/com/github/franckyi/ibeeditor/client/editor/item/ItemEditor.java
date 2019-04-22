package com.github.franckyi.ibeeditor.client.editor.item;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.client.editor.AbstractEditor;
import com.github.franckyi.ibeeditor.client.util.IBENotification;
import com.github.franckyi.ibeeditor.network.IMessage;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTippedArrow;
import net.minecraft.util.text.TextFormatting;

import java.util.function.Consumer;
import java.util.function.Function;

public class ItemEditor extends AbstractEditor {

    private final ItemStack itemStack;
    private final Function<ItemStack, IMessage> packetFactory;
    private final Consumer<ItemStack> action;

    public ItemEditor(ItemStack itemStack, Function<ItemStack, IMessage> packetFactory) {
        this(itemStack, packetFactory, stack -> {
        });
    }

    public ItemEditor(ItemStack itemStack, Function<ItemStack, IMessage> packetFactory, Consumer<ItemStack> action) {
        super("Item Editor");
        this.itemStack = itemStack;
        this.packetFactory = packetFactory;
        this.action = action;
        this.addCategory("General", new GeneralItemCategory(itemStack));
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
        this.addCategory("Tools", new ToolsItemCategory(itemStack));
        this.show();
    }

    @Override
    protected void apply() {
        ItemStack baseStack = itemStack.copy();
        super.apply();
        if (baseStack.equals(itemStack, false)) {
            IBENotification.show(IBENotification.Type.EDITOR, 3, TextFormatting.YELLOW + "Nothing to save.");
        } else {
            this.action.accept(itemStack);
            if (packetFactory != null) {
                IBEEditorMod.CHANNEL.sendToServer(packetFactory.apply(itemStack));
                IBENotification.show(IBENotification.Type.EDITOR, 3, TextFormatting.GREEN + "Item saved.");
            }
        }
    }

}
