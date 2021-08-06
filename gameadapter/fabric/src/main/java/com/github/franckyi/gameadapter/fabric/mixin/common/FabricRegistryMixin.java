package com.github.franckyi.gameadapter.fabric.mixin.common;

import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.IRegistry;
import com.github.franckyi.gameadapter.api.common.IRegistryKey;
import com.github.franckyi.gameadapter.api.common.IRegistryValue;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Keyable;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.IndexedIterable;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;
import java.util.Set;

@Mixin(Registry.class)
public abstract class FabricRegistryMixin<T extends IRegistryValue> implements Codec<T>, Keyable, IndexedIterable<T>, IRegistry<T> {
    @Shadow
    public abstract Set<Map.Entry<RegistryKey<T>, T>> shadow$getEntries();

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Set<Map.Entry<IRegistryKey, T>> getEntries() {
        return (Set) shadow$getEntries();
    }

    @Override
    @SuppressWarnings("unchecked")
    public int getIdFromKey(IIdentifier key) {
        Registry<T> registry = Registry.class.cast(this);
        return getRawId(registry.get((Identifier) key));
    }

    @Override
    @SuppressWarnings("unchecked")
    public IIdentifier getKeyFromId(int id) {
        Registry<T> registry = Registry.class.cast(this);
        return (IIdentifier) registry.getKey(get(id)).map(RegistryKey::getValue).orElse(null);
    }
}
