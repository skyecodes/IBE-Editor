package com.github.franckyi.gameadapter.fabric.common;

import com.github.franckyi.gameadapter.api.common.TagFactory;
import com.github.franckyi.gameadapter.api.common.tag.*;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class FabricTagFactory implements TagFactory {
    public static final TagFactory INSTANCE = new FabricTagFactory();

    private FabricTagFactory() {
    }

    @Override
    public IByteTag createByteTag(byte value) {
        return (IByteTag) NbtByte.of(value);
    }

    @Override
    public IShortTag createShortTag(short value) {
        return (IShortTag) NbtShort.of(value);
    }

    @Override
    public IIntTag createIntTag(int value) {
        return (IIntTag) NbtInt.of(value);
    }

    @Override
    public ILongTag createLongTag(long value) {
        return (ILongTag) NbtLong.of(value);
    }

    @Override
    public IFloatTag createFloatTag(float value) {
        return (IFloatTag) NbtFloat.of(value);
    }

    @Override
    public IDoubleTag createDoubleTag(double value) {
        return (IDoubleTag) NbtDouble.of(value);
    }

    @Override
    public IByteArrayTag createByteArrayTag() {
        return (IByteArrayTag) new NbtByteArray(new byte[0]);
    }

    @Override
    public IByteArrayTag createByteArrayTag(List<Byte> value) {
        return (IByteArrayTag) new NbtByteArray(value);
    }

    @Override
    public IStringTag createStringTag(String value) {
        return (IStringTag) NbtString.of(value);
    }

    @Override
    public IListTag createListTag() {
        return (IListTag) new NbtList();
    }

    @Override
    public IListTag createListTag(Collection<ITag> value) {
        IListTag list = createListTag();
        list.addAll(value);
        return list;
    }

    @Override
    public ICompoundTag createCompoundTag() {
        return (ICompoundTag) new NbtCompound();
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
            return (ICompoundTag) StringNbtReader.parse(snbt);
        } catch (CommandSyntaxException e) {
            return null;
        }
    }

    @Override
    public IIntArrayTag createIntArrayTag() {
        return (IIntArrayTag) new NbtIntArray(new int[0]);
    }

    @Override
    public IIntArrayTag createIntArrayTag(List<Integer> value) {
        return (IIntArrayTag) new NbtIntArray(value);
    }

    @Override
    public ILongArrayTag createLongArrayTag() {
        return (ILongArrayTag) new NbtLongArray(new long[0]);
    }

    @Override
    public ILongArrayTag createLongArrayTag(List<Long> value) {
        return (ILongArrayTag) new NbtLongArray(value);
    }
}
