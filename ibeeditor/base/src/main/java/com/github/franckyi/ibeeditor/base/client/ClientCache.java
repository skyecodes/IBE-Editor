package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.gameadapter.Color;
import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.Registries;
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

    public static void init() {
        itemSuggestions = getSuggestions(Registries.get().getItems());
        itemSelectionItems = getItemSelectionItems(Registries.get().getItems());
        blockSuggestions = getSuggestions(Registries.get().getBlocks());
        blockSelectionItems = getItemSelectionItems(Registries.get().getBlocks());
        attributeSuggestions = getSuggestions(Registries.get().getAttributes());
        attributeSelectionItems = getSelectionItems(Registries.get().getAttributes());
        potionSuggestions = getSuggestions(Registries.get().getPotions());
        potionSelectionItems = getPotionSelectionItems(Registries.get().getPotions());
        effectSuggestions = getSuggestions(Registries.get().getEffects());
        effectSelectionItems = getEffectSelectionItems(Registries.get().getEffects());
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
                .map(attribute -> new SpriteListSelectionItemModel(attribute.getName(), attribute.getId(), () -> Game.getClient().getEffectSprite(attribute.getId())))
                .collect(Collectors.toList());
    }
}
