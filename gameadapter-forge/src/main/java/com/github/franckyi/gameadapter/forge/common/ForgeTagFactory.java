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
        return (IByteTag) ByteTag.valueOf(value);
    }

    @Override
    public IShortTag createShortTag(short value) {
        return (IShortTag) ShortTag.valueOf(value);
    }

    @Override
    public IIntTag createIntTag(int value) {
        return (IIntTag) IntTag.valueOf(value);
    }

    @Override
    public ILongTag createLongTag(long value) {
        return (ILongTag) LongTag.valueOf(value);
    }

    @Override
    public IFloatTag createFloatTag(float value) {
        return (IFloatTag) FloatTag.valueOf(value);
    }

    @Override
    public IDoubleTag createDoubleTag(double value) {
        return (IDoubleTag) DoubleTag.valueOf(value);
    }

    @Override
    public IByteArrayTag createByteArrayTag() {
        return (IByteArrayTag) new ByteArrayTag(new byte[0]);
    }

    @Override
    public IByteArrayTag createByteArrayTag(List<Byte> value) {
        return (IByteArrayTag) new ByteArrayTag(value);
    }

    @Override
    public IStringTag createStringTag(String value) {
        return (IStringTag) StringTag.valueOf(value);
    }

    @Override
    public IListTag createListTag() {
        return IListTag.class.cast(new ListTag());
    }

    @Override
    public IListTag createListTag(Collection<ITag> value) {
        IListTag list = createListTag();
        list.addAll(value);
        return list;
    }

    @Override
    public ICompoundTag createCompoundTag() {
        return (ICompoundTag) new CompoundTag();
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
            return (ICompoundTag) TagParser.parseTag(snbt);
        } catch (CommandSyntaxException e) {
            return null;
        }
    }

    @Override
    public IIntArrayTag createIntArrayTag() {
        return (IIntArrayTag) new IntArrayTag(new int[0]);
    }

    @Override
    public IIntArrayTag createIntArrayTag(List<Integer> value) {
        return (IIntArrayTag) new IntArrayTag(value);
    }

    @Override
    public ILongArrayTag createLongArrayTag() {
        return (ILongArrayTag) new LongArrayTag(new long[0]);
    }

    @Override
    public ILongArrayTag createLongArrayTag(List<Long> value) {
        return (ILongArrayTag) new LongArrayTag(value);
    }
}
