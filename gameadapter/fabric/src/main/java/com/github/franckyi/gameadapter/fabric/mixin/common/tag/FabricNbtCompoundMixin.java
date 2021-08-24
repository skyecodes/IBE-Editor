package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.IListTag;
import com.github.franckyi.gameadapter.api.common.tag.ITag;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Mixin(NbtCompound.class)
public abstract class FabricNbtCompoundMixin implements ICompoundTag {
    @Shadow
    public abstract UUID getUuid(String key);

    @Shadow
    public abstract NbtList shadow$getList(String key, int type);

    @Shadow
    public abstract NbtCompound shadow$getCompound(String key);

    @Shadow
    public abstract void putUuid(String key, UUID value);

    @Shadow
    public abstract boolean shadow$contains(String key, int type);

    @Shadow
    public abstract NbtCompound shadow$copy();

    @Shadow
    @Final
    private Map<String, NbtElement> entries;

    @Override
    public UUID getUUID(String key) {
        return getUuid(key);
    }

    @Override
    public IListTag getList(String key, byte type) {
        return IListTag.class.cast(shadow$getList(key, type));
    }

    @Override
    public ICompoundTag getCompound(String key) {
        return (ICompoundTag) shadow$getCompound(key);
    }

    @Override
    public void putUUID(String key, UUID value) {
        putUuid(key, value);
    }

    @Override
    public void putTag(String key, ITag tag) {
        put(key, tag);
    }

    @Override
    public boolean contains(String key, byte type) {
        return shadow$contains(key, type);
    }

    @Override
    public ICompoundTag copy() {
        return (ICompoundTag) shadow$copy();
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public boolean containsKey(Object key) {
        return entries.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return entries.containsValue(value);
    }

    @Override
    public ITag get(Object key) {
        return (ITag) entries.get(key);
    }

    @Nullable
    @Override
    public ITag put(String key, ITag value) {
        return (ITag) entries.put(key, (NbtElement) value);
    }

    @Override
    public ITag remove(Object key) {
        return (ITag) entries.remove(key);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public void putAll(@NotNull Map<? extends String, ? extends ITag> m) {
        entries.putAll((Map<? extends String, ? extends NbtElement>) m);
    }

    @Override
    public void clear() {
        entries.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return entries.keySet();
    }

    @NotNull
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Collection<ITag> values() {
        return (Collection) entries.values();
    }

    @NotNull
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Set<Entry<String, ITag>> entrySet() {
        return (Set) entries.entrySet();
    }
}
