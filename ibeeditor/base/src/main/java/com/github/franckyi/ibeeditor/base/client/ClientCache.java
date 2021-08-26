package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.gameadapter.Color;
import com.github.franckyi.gameadapter.api.client.ISprite;
import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.RegistryHandler;
import com.github.franckyi.gameadapter.api.common.item.IEnchantment;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.registry.IRegistry;
import com.github.franckyi.gameadapter.api.common.registry.IRegistryValue;
import com.github.franckyi.ibeeditor.base.client.mvc.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class ClientCache {
    private static List<String> itemSuggestions;
    private static List<ItemListSelectionItemModel> itemSelectionItems;
    private static List<String> blockSuggestions;
    private static List<ItemListSelectionItemModel> blockSelectionItems;
    private static List<String> enchantmentSuggestions;
    private static List<EnchantmentListSelectionItemModel> enchantmentSelectionItems;
    private static List<String> attributeSuggestions;
    private static List<ListSelectionItemModel> attributeSelectionItems;
    private static List<String> potionSuggestions;
    private static List<ItemListSelectionItemModel> potionSelectionItems;
    private static List<String> effectSuggestions;
    private static List<SpriteListSelectionItemModel> effectSelectionItems;

    public static void init() {
        itemSuggestions = getSuggestions(RegistryHandler.get().getItemRegistry());
        itemSelectionItems = getItemSelectionItems(RegistryHandler.get().getItemRegistry());
        blockSuggestions = getSuggestions(RegistryHandler.get().getBlockRegistry());
        blockSelectionItems = getItemSelectionItems(RegistryHandler.get().getBlockRegistry());
        enchantmentSuggestions = getSuggestions(RegistryHandler.get().getEnchantmentRegistry());
        enchantmentSelectionItems = getEnchantmentSelectionItems(RegistryHandler.get().getEnchantmentRegistry());
        attributeSuggestions = getSuggestions(RegistryHandler.get().getAttributeRegistry());
        attributeSelectionItems = getSelectionItems(RegistryHandler.get().getAttributeRegistry());
        potionSuggestions = getSuggestions(RegistryHandler.get().getPotionRegistry());
        potionSelectionItems = getPotionSelectionItems(RegistryHandler.get().getPotionRegistry());
        effectSuggestions = getSuggestions(RegistryHandler.get().getEffectRegistry());
        effectSelectionItems = getEffectSelectionItems(RegistryHandler.get().getEffectRegistry());
    }

    public static List<String> getItemSuggestions() {
        return itemSuggestions;
    }

    public static List<ItemListSelectionItemModel> getItemSelectionItems() {
        return itemSelectionItems;
    }

    public static List<String> getBlockSuggestions() {
        return blockSuggestions;
    }

    public static List<ItemListSelectionItemModel> getBlockSelectionItems() {
        return blockSelectionItems;
    }

    public static List<String> getEnchantmentSuggestions() {
        return enchantmentSuggestions;
    }

    public static List<EnchantmentListSelectionItemModel> getSortedEnchantmentSelectionItems(IItemStack target) {
        return enchantmentSelectionItems
                .stream()
                .map(item -> new SortedEnchantmentListSelectionItemModel(item, item.getEnchantment().isCurse(), item.getEnchantment().canApply(target)))
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
                .collect(Collectors.toList());
    }

    public static List<String> getAttributeSuggestions() {
        return attributeSuggestions;
    }

    public static List<ListSelectionItemModel> getAttributeSelectionItems() {
        return attributeSelectionItems;
    }

    public static List<String> getPotionSuggestions() {
        return potionSuggestions;
    }

    public static List<ItemListSelectionItemModel> getPotionSelectionItems() {
        return potionSelectionItems;
    }

    public static List<String> getEffectSuggestions() {
        return effectSuggestions;
    }

    public static List<SpriteListSelectionItemModel> getEffectSelectionItems() {
        return effectSelectionItems;
    }

    private static List<String> getSuggestions(IRegistry<? extends IRegistryValue> registry) {
        List<String> suggestions = new ArrayList<>();
        registry.getEntries().stream()
                .map(e -> e.getKey().getId().toString())
                .forEach(id -> {
                    suggestions.add(id);
                    if (id.startsWith("minecraft:")) {
                        suggestions.add(id.substring(10));
                    }
                })
        ;
        return suggestions;
    }

    private static List<ListSelectionItemModel> getSelectionItems(IRegistry<? extends IRegistryValue> registry) {
        return registry.getEntries().stream()
                .map(e -> new ListSelectionItemModel(e.getValue().getName(), e.getKey().getId()))
                .collect(Collectors.toList());
    }

    private static List<ItemListSelectionItemModel> getItemSelectionItems(IRegistry<? extends IRegistryValue> registry) {
        return registry.getEntries().stream()
                .map(e -> new ItemListSelectionItemModel(e.getValue().getName(), e.getKey().getId(), IItemStack.fromId(e.getKey().getId())))
                .collect(Collectors.toList());
    }

    private static List<EnchantmentListSelectionItemModel> getEnchantmentSelectionItems(IRegistry<IEnchantment> registry) {
        return registry.getEntries().stream()
                .map(e -> new EnchantmentListSelectionItemModel(e.getValue().getName(), e.getKey().getId(), e.getValue(), IItemStack.fromId(getEnchantmentTypeItem(e.getValue()))))
                .collect(Collectors.toList());
    }

    private static IIdentifier getEnchantmentTypeItem(IEnchantment e) {
        switch (e.getTarget()) {
            case IEnchantment.TARGET_ARMOR:
            case IEnchantment.TARGET_WEARABLE:
                return IIdentifier.of("minecraft:iron_chestplate");
            case IEnchantment.TARGET_ARMOR_FEET:
                return IIdentifier.of("minecraft:diamond_boots");
            case IEnchantment.TARGET_ARMOR_LEGS:
                return IIdentifier.of("minecraft:diamond_leggings");
            case IEnchantment.TARGET_ARMOR_CHEST:
                return IIdentifier.of("minecraft:diamond_chestplate");
            case IEnchantment.TARGET_ARMOR_HEAD:
                return IIdentifier.of("minecraft:diamond_helmet");
            case IEnchantment.TARGET_WEAPON:
                return IIdentifier.of("minecraft:diamond_sword");
            case IEnchantment.TARGET_DIGGER:
                return IIdentifier.of("minecraft:diamond_pickaxe");
            case IEnchantment.TARGET_FISHING_ROD:
                return IIdentifier.of("minecraft:fishing_rod");
            case IEnchantment.TARGET_TRIDENT:
                return IIdentifier.of("minecraft:trident");
            case IEnchantment.TARGET_BOW:
                return IIdentifier.of("minecraft:bow");
            case IEnchantment.TARGET_CROSSBOW:
                return IIdentifier.of("minecraft:crossbow");
            case IEnchantment.TARGET_BREAKABLE:
            case IEnchantment.TARGET_VANISHABLE:
                return IIdentifier.of("minecraft:iron_shovel");
        }
        return null;
    }

    private static List<ItemListSelectionItemModel> getPotionSelectionItems(IRegistry<? extends IRegistryValue> registry) {
        return registry.getEntries().stream()
                .map(e -> new ItemListSelectionItemModel(e.getValue().getName(), e.getKey().getId(), IItemStack.fromPotion(e.getKey().getId(), Color.NONE)))
                .collect(Collectors.toList());
    }

    private static List<SpriteListSelectionItemModel> getEffectSelectionItems(IRegistry<? extends IRegistryValue> registry) {
        return registry.getEntries().stream()
                .map(e -> new SpriteListSelectionItemModel(e.getValue().getName(), e.getKey().getId(), () -> ISprite.fromEffect(e.getKey().getId())))
                .collect(Collectors.toList());
    }
}
