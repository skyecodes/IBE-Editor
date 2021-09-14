package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.registry.IRegistry;
import com.github.franckyi.gameadapter.api.common.registry.IRegistryKey;
import com.github.franckyi.gameadapter.api.common.registry.IRegistryValue;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Keyable;
import net.minecraft.core.IdMap;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;
import java.util.Set;

@Mixin(Registry.class)
public abstract class ForgeRegistryMixin<T extends IRegistryValue> implements Codec<T>, Keyable, IdMap<T>, IRegistry<T> {
    @Shadow
    public abstract Set<Map.Entry<ResourceKey<T>, T>> entrySet();

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Set<Map.Entry<IRegistryKey, T>> getEntries() {
        return (Set) entrySet();
    }

    @Override
    @SuppressWarnings("unchecked")
    public int getIdFromKey(IIdentifier key) {
        Registry<T> registry = Registry.class.cast(this);
        return getId(registry.get((ResourceLocation) key));
    }

    @Override
    @SuppressWarnings("unchecked")
    public IIdentifier getKeyFromId(int id) {
        Registry<T> registry = Registry.class.cast(this);
        return (IIdentifier) registry.getKey(byId(id));
    }
}
