package com.github.franckyi.gameadapter.forge.common;

import com.github.franckyi.gameadapter.api.common.IEnchantment;
import com.github.franckyi.gameadapter.api.common.IRegistry;
import com.github.franckyi.gameadapter.api.common.IRegistryValue;
import com.github.franckyi.gameadapter.api.common.RegistryHandler;
import net.minecraft.util.registry.Registry;

public final class ForgeRegistryHandler implements RegistryHandler {
    public static final ForgeRegistryHandler INSTANCE = new ForgeRegistryHandler();

    private ForgeRegistryHandler() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public IRegistry<IRegistryValue> getItemRegistry() {
        return (IRegistry<IRegistryValue>) Registry.ITEM;
    }

    @Override
    @SuppressWarnings("unchecked")
    public IRegistry<IRegistryValue> getBlockRegistry() {
        return (IRegistry<IRegistryValue>) Registry.BLOCK;
    }

    @Override
    @SuppressWarnings("unchecked")
    public IRegistry<IEnchantment> getEnchantmentRegistry() {
        return (IRegistry<IEnchantment>) Registry.ENCHANTMENT;
    }

    @Override
    @SuppressWarnings("unchecked")
    public IRegistry<IRegistryValue> getAttributeRegistry() {
        return (IRegistry<IRegistryValue>) Registry.ATTRIBUTE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public IRegistry<IRegistryValue> getPotionRegistry() {
        return (IRegistry<IRegistryValue>) Registry.POTION;
    }

    @Override
    @SuppressWarnings("unchecked")
    public IRegistry<IRegistryValue> getEffectRegistry() {
        return (IRegistry<IRegistryValue>) Registry.MOB_EFFECT;
    }
}
