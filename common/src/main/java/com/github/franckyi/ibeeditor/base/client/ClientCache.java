package com.github.franckyi.ibeeditor.base.client;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ItemListSelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.ListSelectionItemModel;
import com.github.franckyi.ibeeditor.base.client.mvc.model.SpriteListSelectionItemModel;
import com.github.franckyi.ibeeditor.base.common.TagHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

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

    public static List<String> getItemSuggestions() {
        return itemSuggestions == null ? itemSuggestions = buildSuggestions(Registry.ITEM) : itemSuggestions;
    }

    public static List<ItemListSelectionItemModel> getItemSelectionItems() {
        return itemSelectionItems == null ? itemSelectionItems = buildItemSelectionItems() : itemSelectionItems;
    }

    public static List<String> getBlockSuggestions() {
        return blockSuggestions == null ? blockSuggestions = buildSuggestions(Registry.BLOCK) : blockSuggestions;
    }

    public static List<ItemListSelectionItemModel> getBlockSelectionItems() {
        return blockSelectionItems == null ? blockSelectionItems = buildBlockSelectionItems() : blockSelectionItems;
    }

    public static List<String> getAttributeSuggestions() {
        return attributeSuggestions == null ? attributeSuggestions = buildSuggestions(Registry.ATTRIBUTE) : attributeSuggestions;
    }

    public static List<ListSelectionItemModel> getAttributeSelectionItems() {
        return attributeSelectionItems == null ? attributeSelectionItems = buildAttributeSelectionItems() : attributeSelectionItems;
    }

    public static List<String> getPotionSuggestions() {
        return potionSuggestions == null ? potionSuggestions = buildSuggestions(Registry.POTION) : potionSuggestions;
    }

    public static List<ItemListSelectionItemModel> getPotionSelectionItems() {
        return potionSelectionItems == null ? potionSelectionItems = buildPotionSelectionItems() : potionSelectionItems;
    }

    public static List<String> getEffectSuggestions() {
        return effectSuggestions == null ? effectSuggestions = buildSuggestions(Registry.MOB_EFFECT) : effectSuggestions;
    }

    public static List<SpriteListSelectionItemModel> getEffectSelectionItems() {
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

    private static List<ItemListSelectionItemModel> buildItemSelectionItems() {
        return Registry.ITEM.entrySet().stream()
                .map(e -> new ItemListSelectionItemModel(e.getValue().getDescriptionId(), e.getKey().location(), new ItemStack(e.getValue())))
                .collect(Collectors.toList());
    }

    private static List<ItemListSelectionItemModel> buildBlockSelectionItems() {
        return Registry.BLOCK.entrySet().stream()
                .map(e -> new ItemListSelectionItemModel(e.getValue().getDescriptionId(), e.getKey().location(), new ItemStack(e.getValue())))
                .collect(Collectors.toList());
    }

    private static List<ListSelectionItemModel> buildAttributeSelectionItems() {
        return Registry.ATTRIBUTE.entrySet().stream()
                .map(e -> new ListSelectionItemModel(e.getValue().getDescriptionId(), e.getKey().location()))
                .collect(Collectors.toList());
    }

    private static List<ItemListSelectionItemModel> buildPotionSelectionItems() {
        return Registry.POTION.entrySet().stream()
                .map(e -> new ItemListSelectionItemModel(e.getValue().getName(Items.POTION.getDescriptionId() + ".effect."), e.getKey().location(), TagHelper.fromPotion(e.getKey().location(), Color.NONE)))
                .collect(Collectors.toList());
    }

    private static List<SpriteListSelectionItemModel> buildEffectSelectionItems() {
        return Registry.MOB_EFFECT.entrySet().stream()
                .map(e -> new SpriteListSelectionItemModel(e.getValue().getDescriptionId(), e.getKey().location(), () -> Minecraft.getInstance().getMobEffectTextures().get(e.getValue())))
                .collect(Collectors.toList());
    }
}
