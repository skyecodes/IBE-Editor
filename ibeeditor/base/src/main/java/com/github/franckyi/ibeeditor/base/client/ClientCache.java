package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.gameadapter.Color;
import com.github.franckyi.gameadapter.api.client.ISprite;
import com.github.franckyi.gameadapter.api.common.RegistryHandler;
import com.github.franckyi.gameadapter.api.common.item.IItemStack;
import com.github.franckyi.gameadapter.api.common.registry.IRegistry;
import com.github.franckyi.gameadapter.api.common.registry.IRegistryValue;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemListSelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ListSelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.SpriteListSelectionItemModel;

import java.util.ArrayList;
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
        itemSuggestions = getSuggestions(RegistryHandler.get().getItemRegistry());
        itemSelectionItems = getItemSelectionItems(RegistryHandler.get().getItemRegistry());
        blockSuggestions = getSuggestions(RegistryHandler.get().getBlockRegistry());
        blockSelectionItems = getItemSelectionItems(RegistryHandler.get().getBlockRegistry());
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

    private static List<String> getSuggestions(IRegistry<IRegistryValue> registry) {
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

    private static List<ListSelectionItemModel> getSelectionItems(IRegistry<IRegistryValue> registry) {
        return registry.getEntries().stream()
                .map(e -> new ListSelectionItemModel(e.getValue().getName(), e.getKey().getId()))
                .collect(Collectors.toList());
    }

    private static List<ItemListSelectionItemModel> getItemSelectionItems(IRegistry<IRegistryValue> registry) {
        return registry.getEntries().stream()
                .map(e -> new ItemListSelectionItemModel(e.getValue().getName(), e.getKey().getId(), IItemStack.fromId(e.getKey().getId())))
                .collect(Collectors.toList());
    }

    private static List<ItemListSelectionItemModel> getPotionSelectionItems(IRegistry<IRegistryValue> registry) {
        return registry.getEntries().stream()
                .map(e -> new ItemListSelectionItemModel(e.getValue().getName(), e.getKey().getId(), IItemStack.fromPotion(e.getKey().getId(), Color.NONE)))
                .collect(Collectors.toList());
    }

    private static List<SpriteListSelectionItemModel> getEffectSelectionItems(IRegistry<IRegistryValue> registry) {
        return registry.getEntries().stream()
                .map(e -> new SpriteListSelectionItemModel(e.getValue().getName(), e.getKey().getId(), () -> ISprite.fromEffect(e.getKey().getId())))
                .collect(Collectors.toList());
    }
}
