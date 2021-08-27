package com.github.franckyi.gameadapter.forge.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.IListTag;
import com.github.franckyi.gameadapter.api.common.tag.ITag;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import org.spongepowered.asm.mixin.*;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Mixin(CompoundNBT.class)
@Implements(@Interface(iface = ICompoundTag.class, prefix = "proxy$"))
public abstract class ForgeCompoundNBTMixin implements INBT, Map<String, ITag> {
    @Shadow
    public abstract int getInt(String p_74762_1_);

    @Shadow
    public abstract String getString(String p_74779_1_);

    @Shadow
    public abstract boolean getBoolean(String p_74767_1_);

    @Shadow
    public abstract double getDouble(String p_74769_1_);

    @Shadow
    public abstract UUID getUUID(String p_186857_1_);

    @Shadow
    public abstract ListNBT getList(String key, int type);

    @Shadow
    public abstract CompoundNBT getCompound(String key);

    @Shadow
    public abstract boolean contains(String key, int type);

    @Shadow
    public abstract void putString(String p_74778_1_, String p_74778_2_);

    @Shadow
    public abstract void putInt(String p_74768_1_, int p_74768_2_);

    @Shadow
    public abstract void putBoolean(String p_74757_1_, boolean p_74757_2_);

    @Shadow
    public abstract void putDouble(String p_74780_1_, double p_74780_2_);

    @Shadow
    public abstract void putUUID(String p_186854_1_, UUID p_186854_2_);

    @Shadow
    public abstract void remove(String p_82580_1_);

    @Shadow
    public abstract boolean isEmpty();

    @Shadow
    @Final
    private Map<String, INBT> tags;

    public byte proxy$getType() {
        return getId();
    }

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

    @Intrinsic
    public UUID proxy$getUUID(String key) {
        return getUUID(key);
    }

    public IListTag proxy$getList(String key, byte type) {
        return IListTag.class.cast(getList(key, type));
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

    @Intrinsic
    public void proxy$putUUID(String key, UUID value) {
        putUUID(key, value);
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

    @Override
    public int size() {
        return tags.size();
    }

    @Override
    public boolean containsKey(Object key) {
        return tags.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return tags.containsValue(value);
    }

    @Override
    public ITag get(Object key) {
        return (ITag) tags.get(key);
    }

    @Override
    public ITag put(String key, ITag value) {
        return (ITag) tags.put(key, (INBT) value);
    }

    @Override
    public ITag remove(Object key) {
        return (ITag) tags.remove(key);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public void putAll(@Nonnull Map<? extends String, ? extends ITag> m) {
        tags.putAll((Map<? extends String, ? extends INBT>) m);
    }

    @Override
    public void clear() {
        tags.clear();
    }

    @Override
    public Set<String> keySet() {
        return tags.keySet();
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Collection<ITag> values() {
        return (Collection) tags.values();
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Set<Entry<String, ITag>> entrySet() {
        return (Set) tags.entrySet();
    }
}
