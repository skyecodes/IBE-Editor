package com.github.franckyi.gameadapter.forge.common;

import com.github.franckyi.gameadapter.api.common.Registries;
import com.github.franckyi.gameadapter.api.common.registry.Enchantment;
import com.github.franckyi.gameadapter.api.common.registry.RegistryEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.util.List;
import java.util.stream.Collectors;

public final class ForgeRegistries implements Registries {
    public static final ForgeRegistries INSTANCE = new ForgeRegistries();
    private List<RegistryEntry> items;
    private List<RegistryEntry> blocks;
    private List<Enchantment> enchantments;
    private List<RegistryEntry> attributes;
    private List<RegistryEntry> potions;
    private List<RegistryEntry> effects;

    private ForgeRegistries() {
    }

    @Override
    public void init() {
        items = net.minecraftforge.registries.ForgeRegistries.ITEMS.getEntries().stream().map(entry -> RegistryEntry.of(
                entry.getKey().location().toString(),
                entry.getValue().getDescriptionId()
        )).collect(Collectors.toList());
        blocks = net.minecraftforge.registries.ForgeRegistries.BLOCKS.getEntries().stream().map(entry -> RegistryEntry.of(
                entry.getKey().location().toString(),
                entry.getValue().getDescriptionId()
        )).collect(Collectors.toList());
        enchantments = net.minecraftforge.registries.ForgeRegistries.ENCHANTMENTS.getEntries().stream().map(entry -> Enchantment.of(
                entry.getKey().location().toString(),
                entry.getValue().getDescriptionId(),
                entry.getValue().isCurse(),
                item -> entry.getValue().canEnchant(item.get())
        )).collect(Collectors.toList());
        attributes = net.minecraftforge.registries.ForgeRegistries.ATTRIBUTES.getEntries().stream().map(entry -> RegistryEntry.of(
                entry.getKey().location().toString(),
                entry.getValue().getDescriptionId()
        )).collect(Collectors.toList());
        potions = net.minecraftforge.registries.ForgeRegistries.POTION_TYPES.getEntries().stream().map(entry -> RegistryEntry.of(
                entry.getKey().location().toString(),
                entry.getValue().getName("item.minecraft.potion.effect.")
        )).collect(Collectors.toList());
        effects = net.minecraftforge.registries.ForgeRegistries.POTIONS.getEntries().stream().map(entry -> RegistryEntry.of(
                entry.getKey().location().toString(),
                entry.getValue().getDescriptionId()
        )).collect(Collectors.toList());
    }

    @Override
    public List<RegistryEntry> getItems() {
        return items;
    }

    @Override
    public List<RegistryEntry> getBlocks() {
        return blocks;
    }

    @Override
    public List<Enchantment> getEnchantments() {
        return enchantments;
    }

    @Override
    public List<RegistryEntry> getAttributes() {
        return attributes;
    }

    @Override
    public List<RegistryEntry> getPotions() {
        return potions;
    }

    @Override
    public List<RegistryEntry> getEffects() {
        return effects;
    }

    @Override
    public int getEffectId(String name) {
        return Registry.MOB_EFFECT.getId(net.minecraftforge.registries.ForgeRegistries.POTIONS.getValue(ResourceLocation.tryParse(name)));
    }

    @Override
    public String getEffectFromId(int id) {
        ResourceLocation key = net.minecraftforge.registries.ForgeRegistries.POTIONS.getKey(Registry.MOB_EFFECT.byId(id));
        return key == null ? null : key.toString();
    }
}
