package com.github.franckyi.gameadapter.fabric.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.IListTag;
import com.github.franckyi.gameadapter.api.common.tag.ITag;
import com.github.franckyi.gameadapter.api.common.text.IText;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Mixin(NbtCompound.class)
@Implements(@Interface(iface = ICompoundTag.class, prefix = "proxy$"))
public abstract class FabricNbtCompoundMixin implements NbtElement, Map<String, ITag> {
    @Shadow
    public abstract int getInt(String key);

    @Shadow
    public abstract String getString(String key);

    @Shadow
    public abstract boolean getBoolean(String key);

    @Shadow
    public abstract double getDouble(String key);

    @Shadow
    public abstract UUID getUuid(String key);

    @Shadow
    public abstract NbtList getList(String key, int type);

    @Shadow
    public abstract NbtCompound getCompound(String key);

    @Shadow
    public abstract void putString(String key, String value);

    @Shadow
    public abstract void putInt(String key, int value);

    @Shadow
    public abstract void putBoolean(String key, boolean value);

    @Shadow
    public abstract void putDouble(String key, double value);

    @Shadow
    public abstract void putUuid(String key, UUID value);

    @Shadow
    public abstract boolean contains(String key, int type);

    @Shadow
    public abstract NbtCompound copy();

    @Shadow
    @Final
    private Map<String, NbtElement> entries;

    @Intrinsic
    public int proxy$getInt(String key) {
        return getInt(key);
    }

    @Intrinsic
    public String proxy$getString(String key) {
        return getString(key);
    }

    @Intrinsic
    public boolean proxy$getBoolean(String key) {
        return getBoolean(key);
    }

    @Intrinsic
    public double proxy$getDouble(String key) {
        return getDouble(key);
    }

    public UUID proxy$getUUID(String key) {
        return getUuid(key);
    }

    public IListTag proxy$getList(String key, byte type) {
        return (IListTag) getList(key, type);
    }

    public ICompoundTag proxy$getCompound(String key) {
        return (ICompoundTag) getCompound(key);
    }

    @Intrinsic
    public void proxy$putString(String key, String value) {
        putString(key, value);
    }

    @Intrinsic
    public void proxy$putInt(String key, int value) {
        putInt(key, value);
    }

    @Intrinsic
    public void proxy$putBoolean(String key, boolean value) {
        putBoolean(key, value);
    }

    @Intrinsic
    public void proxy$putDouble(String key, double value) {
        putDouble(key, value);
    }

    public void proxy$putUUID(String key, UUID value) {
        putUuid(key, value);
    }

    public void proxy$putTag(String key, ITag tag) {
        put(key, tag);
    }

    public boolean proxy$contains(String key, byte type) {
        return contains(key, type);
    }

    @Intrinsic
    public void proxy$remove(String key) {
        remove(key);
    }

    @Intrinsic
    public boolean proxy$isEmpty() {
        return isEmpty();
    }

    public ICompoundTag proxy$copy() {
        return (ICompoundTag) copy();
    }

    @Intrinsic
    public byte proxy$getType() {
        return getType();
    }

    public IText proxy$toText() {
        return (IText) toText();
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
