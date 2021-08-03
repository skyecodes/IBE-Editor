package com.github.franckyi.gameadapter.api.common;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.registry.Enchantment;
import com.github.franckyi.gameadapter.api.common.registry.RegistryEntry;

import java.util.List;

public interface Registries {
    static Registries get() {
        return Game.getCommon().getRegistries();
    }

    void init();

    List<RegistryEntry> getItems();

    List<RegistryEntry> getBlocks();

    List<Enchantment> getEnchantments();

    List<RegistryEntry> getAttributes();

    List<RegistryEntry> getPotions();

    List<RegistryEntry> getEffects();

    int getEffectId(String name);

    String getEffectFromId(int id);
}
