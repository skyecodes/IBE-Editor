package com.github.franckyi.ibeeditor.client.util.selection;

import com.github.franckyi.guapi.api.Color;
import com.github.franckyi.ibeeditor.client.util.selection.gui.list.ListSelectionItemModel;
import com.github.franckyi.ibeeditor.client.util.selection.gui.list.item.ItemListSelectionItemModel;
import com.github.franckyi.ibeeditor.client.util.selection.gui.list.item.SpriteListSelectionItemModel;
import com.github.franckyi.ibeeditor.common.TagHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class ListSelectionItemCache {
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

    public static void load() {
        itemSuggestions = getSuggestions(Registry.ITEM);
        itemSelectionItems = getItemSelectionItems(Registry.ITEM);
        blockSuggestions = getSuggestions(Registry.BLOCK);
        blockSelectionItems = getBlockSelectionItems(Registry.BLOCK);
        attributeSuggestions = getSuggestions(Registry.ATTRIBUTE);
        attributeSelectionItems = getAttributeSelectionItems(Registry.ATTRIBUTE);
        potionSuggestions = getSuggestions(Registry.POTION);
        potionSelectionItems = getPotionSelectionItems(Registry.POTION);
        effectSuggestions = getSuggestions(Registry.MOB_EFFECT);
        effectSelectionItems = getEffectSelectionItems(Registry.MOB_EFFECT);
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

    private static List<String> getSuggestions(Registry<?> registry) {
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

    private static List<ItemListSelectionItemModel> getItemSelectionItems(Registry<Item> registry) {
        return registry.entrySet().stream()
                .map(e -> new ItemListSelectionItemModel(e.getValue().getDescriptionId(), e.getKey().location(), new ItemStack(e.getValue())))
                .collect(Collectors.toList());
    }

    private static List<ItemListSelectionItemModel> getBlockSelectionItems(Registry<Block> registry) {
        return registry.entrySet().stream()
                .map(e -> new ItemListSelectionItemModel(e.getValue().getDescriptionId(), e.getKey().location(), new ItemStack(e.getValue())))
                .collect(Collectors.toList());
    }

    private static List<ListSelectionItemModel> getAttributeSelectionItems(Registry<Attribute> registry) {
        return registry.entrySet().stream()
                .map(e -> new ListSelectionItemModel(e.getValue().getDescriptionId(), e.getKey().location()))
                .collect(Collectors.toList());
    }

    private static List<ItemListSelectionItemModel> getPotionSelectionItems(Registry<Potion> registry) {
        return registry.entrySet().stream()
                .map(e -> new ItemListSelectionItemModel(e.getValue().getName(Items.POTION.getDescriptionId() + ".effect."), e.getKey().location(), TagHelper.createPotionItemWithColor(e.getKey().location(), Color.NONE)))
                .collect(Collectors.toList());
    }

    private static List<SpriteListSelectionItemModel> getEffectSelectionItems(Registry<MobEffect> registry) {
        return registry.entrySet().stream()
                .map(e -> new SpriteListSelectionItemModel(e.getValue().getDescriptionId(), e.getKey().location(), () -> Minecraft.getInstance().getMobEffectTextures().get(e.getValue())))
                .collect(Collectors.toList());
    }

}
