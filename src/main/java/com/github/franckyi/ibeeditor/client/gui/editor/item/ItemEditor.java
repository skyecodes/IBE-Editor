package com.github.franckyi.ibeeditor.client.gui.editor.item;

import com.github.franckyi.guapi.node.TexturedButton;
import com.github.franckyi.ibeeditor.client.IBENotification;
import com.github.franckyi.ibeeditor.client.gui.editor.base.AbstractCategory;
import com.github.franckyi.ibeeditor.client.gui.editor.base.CapabilityProviderEditor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.item.TippedArrowItem;
import net.minecraft.util.text.TextFormatting;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class ItemEditor extends CapabilityProviderEditor {
    private final ItemStack itemStack;
    private final Predicate<ItemStack> action;

    public static void withConsumer(ItemStack itemStack, Consumer<ItemStack> action) {
        new ItemEditor(itemStack, itemStack1 -> {
            action.accept(itemStack1);
            return true;
        });
    }

    public static void withPredicate(ItemStack itemStack, Predicate<ItemStack> action) {
        new ItemEditor(itemStack, action);
    }

    private ItemEditor(ItemStack itemStack, Predicate<ItemStack> action) {
        super("Item Editor :");
        this.itemStack = itemStack;
        this.action = action;
        header.getChildren().add(new TexturedButton(itemStack));
        this.addCategory("General", new GeneralItemCategory(itemStack));
        if (itemStack.getItem() instanceof PotionItem || itemStack.getItem() instanceof TippedArrowItem) {
            this.addCategory("Potion effects", new PotionCategory(itemStack));
        }
        this.applyConfigurations(this.getCapabilityConfigurations(), itemStack);
        this.addCategory("Enchantments", new EnchantmentsCategory(itemStack));
        this.addCategory("Attribute modifiers", new AttributeModifiersCategory(itemStack));
        this.addCategory("Hide Flags", new HideFlagsCategory(itemStack));
        if (itemStack.getItem() instanceof BlockItem) {
            this.addCategory("Can place on", new BlockCategory(itemStack, "CanPlaceOn"));
        }
        this.addCategory("Can destroy", new BlockCategory(itemStack, "CanDestroy"));
        this.addCategory("Tools", new ToolsItemCategory(itemStack));
        this.show();
    }

    @Override
    protected void apply() {
        ItemStack baseStack = itemStack.copy();
        propertiesList.subList(1, propertiesList.size()).forEach(AbstractCategory::apply);
        propertiesList.get(0).apply();
        header.getChildren().set(1, new TexturedButton(itemStack));
        if (baseStack.equals(itemStack, false)) {
            IBENotification.show(IBENotification.Type.EDITOR, 3, TextFormatting.YELLOW + "Nothing to save.");
        } else {
            if (this.action.test(itemStack)) {
                IBENotification.show(IBENotification.Type.EDITOR, 3, TextFormatting.GREEN + "Item saved.");
            }
        }
    }

}
