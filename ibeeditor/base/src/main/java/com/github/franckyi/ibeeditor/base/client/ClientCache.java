package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.registry.RegistryEntry;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemSelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.SelectionItemModel;

import java.util.List;
import java.util.stream.Collectors;

public final class ClientCache {
    private static List<String> itemSuggestions;
    private static List<ItemSelectionItemModel> itemSelectionItems;
    private static List<String> blockSuggestions;
    private static List<ItemSelectionItemModel> blockSelectionItems;
    private static List<String> attributeSuggestions;
    private static List<SelectionItemModel> attributeSelectionItems;

    public static List<String> getItemSuggestions() {
        if (itemSuggestions == null) {
            itemSuggestions = getSuggestions(Game.getCommon().getRegistries().getItems());
        }
        return itemSuggestions;
    }

    public static List<ItemSelectionItemModel> getItemSelectionItems() {
        if (itemSelectionItems == null) {
            itemSelectionItems = getSelectionItemsWithItem(Game.getCommon().getRegistries().getItems());
        }
        return itemSelectionItems;
    }

    public static List<String> getBlockSuggestions() {
        if (blockSuggestions == null) {
            blockSuggestions = getSuggestions(Game.getCommon().getRegistries().getBlocks());
        }
        return blockSuggestions;
    }

    public static List<ItemSelectionItemModel> getBlockSelectionItems() {
        if (blockSelectionItems == null) {
            blockSelectionItems = getSelectionItemsWithItem(Game.getCommon().getRegistries().getBlocks());
        }
        return blockSelectionItems;
    }

    public static List<String> getAttributeSuggestions() {
        if (attributeSuggestions == null) {
            attributeSuggestions = getSuggestions(Game.getCommon().getRegistries().getAttributes());
        }
        return attributeSuggestions;
    }

    public static List<SelectionItemModel> getAttributeSelectionItems() {
        if (attributeSelectionItems == null) {
            attributeSelectionItems = getSelectionItems(Game.getCommon().getRegistries().getAttributes());
        }
        return attributeSelectionItems;
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

    private static List<SelectionItemModel> getSelectionItems(List<? extends RegistryEntry> list) {
        return list.stream()
                .map(attribute -> new SelectionItemModel(attribute.getName(), attribute.getId()))
                .collect(Collectors.toList());
    }

    private static List<ItemSelectionItemModel> getSelectionItemsWithItem(List<? extends RegistryEntry> list) {
        return list.stream()
                .map(attribute -> new ItemSelectionItemModel(attribute.getName(), attribute.getId(), Game.getCommon().createItem(attribute.getId())))
                .collect(Collectors.toList());
    }
}
