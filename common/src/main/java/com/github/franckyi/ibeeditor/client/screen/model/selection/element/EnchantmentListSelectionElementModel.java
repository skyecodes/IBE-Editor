package com.github.franckyi.ibeeditor.client.screen.model.selection.element;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class EnchantmentListSelectionElementModel extends ItemListSelectionElementModel {
    private final Enchantment enchantment;

    public EnchantmentListSelectionElementModel(String name, ResourceLocation id, Enchantment enchantment, ItemStack item) {
        super(name, id, item);
        this.enchantment = enchantment;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }
}
