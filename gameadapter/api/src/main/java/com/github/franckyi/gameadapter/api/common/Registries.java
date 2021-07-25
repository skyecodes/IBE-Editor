package com.github.franckyi.gameadapter.api.common;

import com.github.franckyi.gameadapter.api.common.registry.Enchantment;
import com.github.franckyi.gameadapter.api.common.registry.RegistryEntry;

import java.util.List;

public interface Registries {
    List<RegistryEntry> getItems();

    List<RegistryEntry> getBlocks();

    List<Enchantment> getEnchantments();

    List<RegistryEntry> getAttributes();

    List<RegistryEntry> getPotions();
}
