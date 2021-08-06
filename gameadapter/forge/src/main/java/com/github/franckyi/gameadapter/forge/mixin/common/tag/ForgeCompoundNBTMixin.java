package com.github.franckyi.gameadapter.forge.mixin.common.tag;

import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.tag.IListTag;
import com.github.franckyi.gameadapter.api.common.tag.ITag;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Mixin(CompoundNBT.class)
public abstract class ForgeCompoundNBTMixin implements ICompoundTag {
    @Shadow
    public abstract byte getId();

    @Shadow
    public abstract ListNBT shadow$getList(String key, int type);

    @Shadow
    public abstract CompoundNBT shadow$getCompound(String key);

    @Shadow
    public abstract boolean shadow$contains(String key, int type);

    @Shadow
    public abstract CompoundNBT shadow$copy();

    @Shadow
    @Final
    private Map<String, INBT> tags;

    @Override
    public byte getType() {
        return getId();
    }

    @Override
    public IListTag getList(String key, byte type) {
        return (IListTag) shadow$getList(key, type);
    }

    @Override
    public ICompoundTag getCompound(String key) {
        return (ICompoundTag) shadow$getCompound(key);
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
