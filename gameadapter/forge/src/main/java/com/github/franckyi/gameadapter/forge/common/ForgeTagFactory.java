package com.github.franckyi.gameadapter.forge.common;

import com.github.franckyi.gameadapter.api.common.TagFactory;
import com.github.franckyi.gameadapter.api.common.tag.*;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class ForgeTagFactory implements TagFactory {
    public static final TagFactory INSTANCE = new ForgeTagFactory();

    private ForgeTagFactory() {
    }

    @Override
    public IByteTag createByteTag(byte value) {
        return (IByteTag) ByteNBT.valueOf(value);
    }

    @Override
    public IShortTag createShortTag(short value) {
        return (IShortTag) ShortNBT.valueOf(value);
    }

    @Override
    public IIntTag createIntTag(int value) {
        return (IIntTag) IntNBT.valueOf(value);
    }

    @Override
    public ILongTag createLongTag(long value) {
        return (ILongTag) LongNBT.valueOf(value);
    }

    @Override
    public IFloatTag createFloatTag(float value) {
        return (IFloatTag) FloatNBT.valueOf(value);
    }

    @Override
    public IDoubleTag createDoubleTag(double value) {
        return (IDoubleTag) DoubleNBT.valueOf(value);
    }

    @Override
    public IByteArrayTag createByteArrayTag() {
        return (IByteArrayTag) new ByteArrayNBT(new byte[0]);
    }

    @Override
    public IByteArrayTag createByteArrayTag(List<Byte> value) {
        return (IByteArrayTag) new ByteArrayNBT(value);
    }

    @Override
    public IStringTag createStringTag(String value) {
        return (IStringTag) StringNBT.valueOf(value);
    }

    @Override
    public IListTag createListTag() {
        return (IListTag) new ListNBT();
    }

    @Override
    public IListTag createListTag(Collection<ITag> value) {
        IListTag list = createListTag();
        list.addAll(value);
        return list;
    }

    @Override
    public ICompoundTag createCompoundTag() {
        return (ICompoundTag) new CompoundNBT();
    }

    @Override
    public ICompoundTag createCompoundTag(Map<String, ITag> value) {
        ICompoundTag compound = createCompoundTag();
        compound.putAll(value);
        return compound;
    }

    @Override
    public ICompoundTag parseCompoundTag(String snbt) {
        try {
            return (ICompoundTag) JsonToNBT.parseTag(snbt);
        } catch (CommandSyntaxException e) {
            return null;
        }
    }

    @Override
    public IIntArrayTag createIntArrayTag() {
        return (IIntArrayTag) new IntArrayNBT(new int[0]);
    }

    @Override
    public IIntArrayTag createIntArrayTag(List<Integer> value) {
        return (IIntArrayTag) new IntArrayNBT(value);
    }

    @Override
    public ILongArrayTag createLongArrayTag() {
        return (ILongArrayTag) new LongArrayNBT(new long[0]);
    }

    @Override
    public ILongArrayTag createLongArrayTag(List<Long> value) {
        return (ILongArrayTag) new LongArrayNBT(value);
    }
}
