package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.ibeeditor.client.screen.model.selection.element.*;
import com.github.franckyi.ibeeditor.common.ColoredItemHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.ArrayList;
import java.util.List;

public final class ClientCache {
    private static List<String> itemSuggestions;
    private static List<ItemListSelectionElementModel> itemSelectionItems;
    private static List<String> blockSuggestions;
    private static List<ItemListSelectionElementModel> blockSelectionItems;
    private static List<String> enchantmentSuggestions;
    private static List<EnchantmentListSelectionElementModel> enchantmentSelectionItems;
    private static List<String> attributeSuggestions;
    private static List<ListSelectionElementModel> attributeSelectionItems;
    private static List<String> potionSuggestions;
    private static List<ItemListSelectionElementModel> potionSelectionItems;
    private static List<String> effectSuggestions;
    private static List<SpriteListSelectionElementModel> effectSelectionItems;

    public static List<String> getItemSuggestions() {
        return itemSuggestions == null ? itemSuggestions = buildSuggestions(Registry.ITEM) : itemSuggestions;
    }

    public static List<ItemListSelectionElementModel> getItemSelectionItems() {
        return itemSelectionItems == null ? itemSelectionItems = buildItemSelectionItems() : itemSelectionItems;
    }

    public static List<String> getBlockSuggestions() {
        return blockSuggestions == null ? blockSuggestions = buildSuggestions(Registry.BLOCK) : blockSuggestions;
    }

    public static List<ItemListSelectionElementModel> getBlockSelectionItems() {
        return blockSelectionItems == null ? blockSelectionItems = buildBlockSelectionItems() : blockSelectionItems;
    }

    public static List<String> getEnchantmentSuggestions() {
        return enchantmentSuggestions == null ? enchantmentSuggestions = buildSuggestions(Registry.ENCHANTMENT) : enchantmentSuggestions;
    }

    public static List<SortedEnchantmentListSelectionElementModel> getSortedEnchantmentSelectionItems(ItemStack target) {
        if (enchantmentSelectionItems == null) enchantmentSelectionItems = buildEnchantmentSelectionItems();
        return enchantmentSelectionItems
                .stream()
                .map(item -> new SortedEnchantmentListSelectionElementModel(item, item.getEnchantment().isCurse(), item.getEnchantment().canEnchant(target)))
                .sorted((o1, o2) -> {
                    if (o1.isCurse()) {
                        if (o2.isCurse()) {
                            return o1.getName().compareTo(o2.getName());
                        } else {
                            return 1;
                        }
                    } else {
                        if (o2.isCurse()) {
                            return -1;
                        } else {
                            if (o1.canApply()) {
                                if (o2.canApply()) {
                                    return o1.getName().compareTo(o2.getName());
                                } else {
                                    return -1;
                                }
                            } else {
                                if (o2.canApply()) {
                                    return 1;
                                } else {
                                    return o1.getName().compareTo(o2.getName());
                                }
                            }
                        }
                    }
                })
                .toList();
    }

    public static List<String> getAttributeSuggestions() {
        return attributeSuggestions == null ? attributeSuggestions = buildSuggestions(Registry.ATTRIBUTE) : attributeSuggestions;
    }

    public static List<ListSelectionElementModel> getAttributeSelectionItems() {
        return attributeSelectionItems == null ? attributeSelectionItems = buildAttributeSelectionItems() : attributeSelectionItems;
    }

    public static List<String> getPotionSuggestions() {
        return potionSuggestions == null ? potionSuggestions = buildSuggestions(Registry.POTION) : potionSuggestions;
    }

    public static List<ItemListSelectionElementModel> getPotionSelectionItems() {
        return potionSelectionItems == null ? potionSelectionItems = buildPotionSelectionItems() : potionSelectionItems;
    }

    public static List<String> getEffectSuggestions() {
        return effectSuggestions == null ? effectSuggestions = buildSuggestions(Registry.MOB_EFFECT) : effectSuggestions;
    }

    public static List<SpriteListSelectionElementModel> getEffectSelectionItems() {
        return effectSelectionItems == null ? effectSelectionItems = buildEffectSelectionItems() : effectSelectionItems;
    }

    private static List<String> buildSuggestions(Registry<?> registry) {
        List<String> suggestions = new ArrayList<>();
        registry.entrySet().stream()
                .map(e -> e.getKey().location().toString())
                .forEach(id -> {
                    suggestions.add(id);
                    if (id.startsWith("minecraft:")) {
                        suggestions.add(id.substring(10));
                    }
                })
        ;
        return suggestions;
    }

    private static List<ItemListSelectionElementModel> buildItemSelectionItems() {
        return Registry.ITEM.entrySet().stream()
                .map(e -> new ItemListSelectionElementModel(e.getValue().getDescriptionId(), e.getKey().location(), new ItemStack(e.getValue())))
                .toList();
    }

    private static List<ItemListSelectionElementModel> buildBlockSelectionItems() {
        return Registry.BLOCK.entrySet().stream()
                .map(e -> new ItemListSelectionElementModel(e.getValue().getDescriptionId(), e.getKey().location(), new ItemStack(e.getValue())))
                .toList();
    }

    private static List<EnchantmentListSelectionElementModel> buildEnchantmentSelectionItems() {
        return Registry.ENCHANTMENT.entrySet().stream()
                .map(e -> new EnchantmentListSelectionElementModel(e.getValue().getDescriptionId(), e.getKey().location(), e.getValue(), new ItemStack(getEnchantmentTypeItem(e.getValue()))))
                .toList();
    }

    private static Item getEnchantmentTypeItem(Enchantment e) {
        // Not using a switch statement: Forge extends the enum and allows new enchantment categories to be registered, therefore the switch statement throws an error.
        var c = e.category;
        if (c == EnchantmentCategory.ARMOR || c == EnchantmentCategory.WEARABLE) {
            return Items.IRON_CHESTPLATE;
        } else if (c == EnchantmentCategory.ARMOR_FEET) {
            return Items.DIAMOND_BOOTS;
        } else if (c == EnchantmentCategory.ARMOR_LEGS) {
            return Items.DIAMOND_LEGGINGS;
        } else if (c == EnchantmentCategory.ARMOR_CHEST) {
            return Items.DIAMOND_CHESTPLATE;
        } else if (c == EnchantmentCategory.ARMOR_HEAD) {
            return Items.DIAMOND_HELMET;
        } else if (c == EnchantmentCategory.WEAPON) {
            return Items.DIAMOND_SWORD;
        } else if (c == EnchantmentCategory.DIGGER) {
            return Items.DIAMOND_PICKAXE;
        } else if (c == EnchantmentCategory.FISHING_ROD) {
            return Items.FISHING_ROD;
        } else if (c == EnchantmentCategory.TRIDENT) {
            return Items.TRIDENT;
        } else if (c == EnchantmentCategory.BOW) {
            return Items.BOW;
        } else if (c == EnchantmentCategory.CROSSBOW) {
            return Items.CROSSBOW;
        } else if (c == EnchantmentCategory.BREAKABLE || c == EnchantmentCategory.VANISHABLE) {
            return Items.IRON_SHOVEL;
        }
        return Items.AIR;
    }

    private static List<ListSelectionElementModel> buildAttributeSelectionItems() {
        return Registry.ATTRIBUTE.entrySet().stream()
                .map(e -> new ListSelectionElementModel(e.getValue().getDescriptionId(), e.getKey().location()))
                .toList();
    }

    private static List<ItemListSelectionElementModel> buildPotionSelectionItems() {
        return Registry.POTION.entrySet().stream()
                .map(e -> new ItemListSelectionElementModel(e.getValue().getName(Items.POTION.getDescriptionId() + ".effect."), e.getKey().location(), ColoredItemHelper.createColoredPotionItem(e.getKey().location(), Color.NONE)))
                .toList();
    }

    private static List<SpriteListSelectionElementModel> buildEffectSelectionItems() {
        return Registry.MOB_EFFECT.entrySet().stream()
                .map(e -> new SpriteListSelectionElementModel(e.getValue().getDescriptionId(), e.getKey().location(), () -> Minecraft.getInstance().getMobEffectTextures().get(e.getValue())))
                .toList();
    }

}
