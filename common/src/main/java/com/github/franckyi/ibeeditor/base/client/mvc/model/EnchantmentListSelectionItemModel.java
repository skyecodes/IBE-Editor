package com.github.franckyi.ibeeditor.base.client.mvc.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

public class EnchantmentListSelectionItemModel extends ItemListSelectionItemModel {
    private final Enchantment enchantment;

    public EnchantmentListSelectionItemModel(String name, ResourceLocation id, Enchantment enchantment, ItemStack item) {
        super(name, id, item);
        this.enchantment = enchantment;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }
}
