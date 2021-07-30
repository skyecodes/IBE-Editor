package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.gameadapter.Color;
import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.registry.RegistryEntry;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemListSelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ListSelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.SpriteListSelectionItemModel;

import java.util.List;
import java.util.stream.Collectors;

public final class ClientCache {
    private static List<String> itemSuggestions;
    private static List<ItemListSelectionItemModel> itemSelectionItems;
    private static List<String> blockSuggestions;
    private static List<ItemListSelectionItemModel> blockSelectionItems;
    private static List<String> attributeSuggestions;
    private static List<ListSelectionItemModel> attributeSelectionItems;
    private static List<String> potionSuggestions;
    private static List<ItemListSelectionItemModel> potionSelectionItems;
    private static List<String> effectSuggestions;
    private static List<SpriteListSelectionItemModel> effectSelectionItems;

    public static List<String> getItemSuggestions() {
        if (itemSuggestions == null) {
            itemSuggestions = getSuggestions(Game.getCommon().getRegistries().getItems());
        }
        return itemSuggestions;
    }

    public static List<ItemListSelectionItemModel> getItemSelectionItems() {
        if (itemSelectionItems == null) {
            itemSelectionItems = getItemSelectionItems(Game.getCommon().getRegistries().getItems());
        }
        return itemSelectionItems;
    }

    public static List<String> getBlockSuggestions() {
        if (blockSuggestions == null) {
            blockSuggestions = getSuggestions(Game.getCommon().getRegistries().getBlocks());
        }
        return blockSuggestions;
    }

    public static List<ItemListSelectionItemModel> getBlockSelectionItems() {
        if (blockSelectionItems == null) {
            blockSelectionItems = getItemSelectionItems(Game.getCommon().getRegistries().getBlocks());
        }
        return blockSelectionItems;
    }

    public static List<String> getAttributeSuggestions() {
        if (attributeSuggestions == null) {
            attributeSuggestions = getSuggestions(Game.getCommon().getRegistries().getAttributes());
        }
        return attributeSuggestions;
    }

    public static List<ListSelectionItemModel> getAttributeSelectionItems() {
        if (attributeSelectionItems == null) {
            attributeSelectionItems = getSelectionItems(Game.getCommon().getRegistries().getAttributes());
        }
        return attributeSelectionItems;
    }

    public static List<String> getPotionSuggestions() {
        if (potionSuggestions == null) {
            potionSuggestions = getSuggestions(Game.getCommon().getRegistries().getPotions());
        }
        return potionSuggestions;
    }

    public static List<ItemListSelectionItemModel> getPotionSelectionItems() {
        if (potionSelectionItems == null) {
            potionSelectionItems = getPotionSelectionItems(Game.getCommon().getRegistries().getPotions());
        }
        return potionSelectionItems;
    }

    public static List<String> getEffectSuggestions() {
        if (effectSuggestions == null) {
            effectSuggestions = getSuggestions(Game.getCommon().getRegistries().getEffects());
        }
        return effectSuggestions;
    }

    public static List<SpriteListSelectionItemModel> getEffectSelectionItems() {
        if (effectSelectionItems == null) {
            effectSelectionItems = getEffectSelectionItems(Game.getCommon().getRegistries().getEffects());
        }
        return effectSelectionItems;
    }

    private static List<String> getSuggestions(List<? extends RegistryEntry> list) {
        List<String> suggestions = list.stream().map(RegistryEntry::getId).collect(Collectors.toList());
        list.forEach(e -> {
            if (e.getId().startsWith("minecraft:")) {
                suggestions.add(e.getId().substring(10));
            }
        });
        return suggestions;
    }

    private static List<ListSelectionItemModel> getSelectionItems(List<? extends RegistryEntry> list) {
        return list.stream()
                .map(attribute -> new ListSelectionItemModel(attribute.getName(), attribute.getId()))
                .collect(Collectors.toList());
    }

    private static List<ItemListSelectionItemModel> getItemSelectionItems(List<? extends RegistryEntry> list) {
        return list.stream()
                .map(attribute -> new ItemListSelectionItemModel(attribute.getName(), attribute.getId(), Game.getCommon().createItem(attribute.getId())))
                .collect(Collectors.toList());
    }

    private static List<ItemListSelectionItemModel> getPotionSelectionItems(List<? extends RegistryEntry> list) {
        return list.stream()
                .map(attribute -> new ItemListSelectionItemModel(attribute.getName(), attribute.getId(), Game.getCommon().createPotionItem(attribute.getId(), Color.NONE)))
                .collect(Collectors.toList());
    }

    private static List<SpriteListSelectionItemModel> getEffectSelectionItems(List<? extends RegistryEntry> list) {
        return list.stream()
                .map(attribute -> new SpriteListSelectionItemModel(attribute.getName(), attribute.getId(), Game.getClient().getEffectSprite(attribute.getId())))
                .collect(Collectors.toList());
    }
}
