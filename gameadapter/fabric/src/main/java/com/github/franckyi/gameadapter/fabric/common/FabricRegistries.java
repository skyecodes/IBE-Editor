package com.github.franckyi.gameadapter.fabric.common;

import com.github.franckyi.gameadapter.api.common.Registries;
import com.github.franckyi.gameadapter.api.common.registry.Enchantment;
import com.github.franckyi.gameadapter.api.common.registry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.List;
import java.util.stream.Collectors;

public final class FabricRegistries implements Registries {
    public static final FabricRegistries INSTANCE = new FabricRegistries();
    private List<RegistryEntry> items;
    private List<RegistryEntry> blocks;
    private List<Enchantment> enchantments;
    private List<RegistryEntry> attributes;
    private List<RegistryEntry> potions;
    private List<RegistryEntry> effects;

    private FabricRegistries() {
    }

    @Override
    public List<RegistryEntry> getItems() {
        if (items == null) {
            items = Registry.ITEM.getEntries().stream().map(entry -> RegistryEntry.of(
                    entry.getKey().getValue().toString(),
                    entry.getValue().getTranslationKey())
            ).collect(Collectors.toList());
        }
        return items;
    }

    @Override
    public List<RegistryEntry> getBlocks() {
        if (blocks == null) {
            blocks = Registry.BLOCK.getEntries().stream().map(entry -> RegistryEntry.of(
                    entry.getKey().getValue().toString(),
                    entry.getValue().getTranslationKey())
            ).collect(Collectors.toList());
        }
        return blocks;
    }

    @Override
    public List<Enchantment> getEnchantments() {
        if (enchantments == null) {
            enchantments = Registry.ENCHANTMENT.getEntries().stream().map(entry -> Enchantment.of(
                    entry.getKey().getValue().toString(),
                    entry.getValue().getTranslationKey(),
                    entry.getValue().isCursed(),
                    item -> entry.getValue().isAcceptableItem(item.get()))
            ).collect(Collectors.toList());
        }
        return enchantments;
    }

    @Override
    public List<RegistryEntry> getAttributes() {
        if (attributes == null) {
            attributes = Registry.ATTRIBUTE.getEntries().stream().map(entry -> RegistryEntry.of(
                    entry.getKey().getValue().toString(),
                    entry.getValue().getTranslationKey())
            ).collect(Collectors.toList());
        }
        return attributes;
    }

    @Override
    public List<RegistryEntry> getPotions() {
        if (potions == null) {
            potions = Registry.POTION.getEntries().stream().map(entry -> RegistryEntry.of(
                    entry.getKey().getValue().toString(),
                    entry.getValue().finishTranslationKey("item.minecraft.potion.effect.")
            )).collect(Collectors.toList());
        }
        return potions;
    }

    @Override
    public List<RegistryEntry> getEffects() {
        if (effects == null) {
            effects = Registry.STATUS_EFFECT.getEntries().stream().map(entry -> RegistryEntry.of(
                    entry.getKey().getValue().toString(),
                    entry.getValue().getTranslationKey()
            )).collect(Collectors.toList());
        }
        return effects;
    }

    @Override
    public int getEffectId(String name) {
        return Registry.STATUS_EFFECT.getRawId(Registry.STATUS_EFFECT.get(Identifier.tryParse(name)));
    }

    @Override
    public String getEffectFromId(int id) {
        return Registry.STATUS_EFFECT.getKey(Registry.STATUS_EFFECT.get(id)).map(key -> key.getValue().toString()).orElse(null);
    }
}
