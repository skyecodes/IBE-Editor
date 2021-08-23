package com.github.franckyi.gameadapter.fabric.common;

import com.github.franckyi.gameadapter.api.common.RegistryHandler;
import com.github.franckyi.gameadapter.api.common.item.IEnchantment;
import com.github.franckyi.gameadapter.api.common.registry.IRegistry;
import com.github.franckyi.gameadapter.api.common.registry.IRegistryValue;
import net.minecraft.util.registry.Registry;

public final class FabricRegistryHandler implements RegistryHandler {
    public static final FabricRegistryHandler INSTANCE = new FabricRegistryHandler();

    private FabricRegistryHandler() {
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
        return (IRegistry<IRegistryValue>) Registry.STATUS_EFFECT;
    }
}
