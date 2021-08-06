package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.IIdentifier;
import com.github.franckyi.gameadapter.api.common.IRegistry;
import com.github.franckyi.gameadapter.api.common.IRegistryKey;
import com.github.franckyi.gameadapter.api.common.IRegistryValue;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Keyable;
import net.minecraft.util.IObjectIntIterable;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;
import java.util.Set;

@Mixin(Registry.class)
public abstract class ForgeRegistryMixin<T extends IRegistryValue> implements Codec<T>, Keyable, IObjectIntIterable<T>, IRegistry<T> {
    @Shadow
    public abstract Set<Map.Entry<RegistryKey<T>, T>> entrySet();

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
