package com.github.franckyi.gameadapter.api.common;

import com.github.franckyi.gameadapter.Game;

public interface RegistryHandler {
    static RegistryHandler get() {
        return Game.getCommon().getRegistryHandler();
    }

    IRegistry<IRegistryValue> getItemRegistry();

    IRegistry<IRegistryValue> getBlockRegistry();

    IRegistry<IEnchantment> getEnchantmentRegistry();

    IRegistry<IRegistryValue> getAttributeRegistry();

    IRegistry<IRegistryValue> getPotionRegistry();

    IRegistry<IRegistryValue> getEffectRegistry();
}
