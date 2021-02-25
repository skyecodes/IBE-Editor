package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.TagFactory;
import com.github.franckyi.gamehooks.util.common.tag.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

public final class ForgeTagFactory implements TagFactory<CompoundNBT> {
    public static final TagFactory<CompoundNBT> INSTANCE = new ForgeTagFactory();

    @Override
    public CompoundNBT parseObject(ObjectTag obj) {
        if (obj == null) return null;
        CompoundNBT c = new CompoundNBT();
        obj.getValue().forEach((s, tag) -> c.put(s, parseTag(tag)));
        return c;
    }

    @Override
    public ObjectTag parseCompound(CompoundNBT c) {
        if (c == null) return null;
        ObjectTag tag = new ObjectTag();
        for (String key : c.keySet()) {
            net.minecraft.nbt.INBT tag1 = c.get(key);
            tag.getValue().put(key, parseTag(tag1));
        }
        return tag;
    }

    private Tag<?> parseTag(net.minecraft.nbt.INBT tag) {
        switch (tag.getType().getTagName()) {
            case Tag.BYTE_NAME:
                return new ByteTag(((net.minecraft.nbt.ByteNBT) tag).getByte());
            case Tag.SHORT_NAME:
                return new ShortTag(((net.minecraft.nbt.ShortNBT) tag).getShort());
            case Tag.INT_NAME:
                return new IntTag(((net.minecraft.nbt.IntNBT) tag).getInt());
            case Tag.LONG_NAME:
                return new LongTag(((net.minecraft.nbt.LongNBT) tag).getLong());
            case Tag.FLOAT_NAME:
                return new FloatTag(((net.minecraft.nbt.FloatNBT) tag).getFloat());
            case Tag.DOUBLE_NAME:
                return new DoubleTag(((net.minecraft.nbt.DoubleNBT) tag).getDouble());
            case Tag.BYTE_ARRAY_NAME:
                return new ByteArrayTag(((net.minecraft.nbt.ByteArrayNBT) tag).getByteArray());
            case Tag.STRING_NAME:
                return new StringTag(tag.getString());
            case Tag.LIST_NAME:
                return parseList((ListNBT) tag);
            case Tag.COMPOUND_NAME:
                return parseCompound((CompoundNBT) tag);
            case Tag.INT_ARRAY_NAME:
                return new IntArrayTag(((net.minecraft.nbt.IntArrayNBT) tag).getIntArray());
            case Tag.LONG_ARRAY_NAME:
                return new LongArrayTag(((net.minecraft.nbt.LongArrayNBT) tag).getAsLongArray());
        }
        return null;
    }

    private net.minecraft.nbt.INBT parseTag(Tag<?> tag) {
        switch (tag.getType()) {
            case Tag.BYTE_ID:
                return net.minecraft.nbt.ByteNBT.valueOf(((ByteTag) tag).getValue());
            case Tag.SHORT_ID:
                return net.minecraft.nbt.ShortNBT.valueOf(((ShortTag) tag).getValue());
            case Tag.INT_ID:
                return net.minecraft.nbt.IntNBT.valueOf(((IntTag) tag).getValue());
            case Tag.LONG_ID:
                return net.minecraft.nbt.LongNBT.valueOf(((LongTag) tag).getValue());
            case Tag.FLOAT_ID:
                return net.minecraft.nbt.FloatNBT.valueOf(((FloatTag) tag).getValue());
            case Tag.DOUBLE_ID:
                return net.minecraft.nbt.DoubleNBT.valueOf(((DoubleTag) tag).getValue());
            case Tag.BYTE_ARRAY_ID:
                return new net.minecraft.nbt.ByteArrayNBT(((ByteArrayTag) tag).getValue());
            case Tag.STRING_ID:
                return net.minecraft.nbt.StringNBT.valueOf(((StringTag) tag).getValue());
            case Tag.LIST_ID:
                return parseArray((ArrayTag) tag);
            case Tag.COMPOUND_ID:
                return parseObject((ObjectTag) tag);
            case Tag.INT_ARRAY_ID:
                return new net.minecraft.nbt.IntArrayNBT(((IntArrayTag) tag).getValue());
            case Tag.LONG_ARRAY_ID:
                return new net.minecraft.nbt.LongArrayNBT(((LongArrayTag) tag).getValue());
        }
        return null;
    }

    private ListNBT parseArray(ArrayTag tag) {
        ListNBT listTag = new ListNBT();
        for (Tag<?> e : tag.getValue()) {
            listTag.add(parseTag(e));
        }
        return listTag;
    }

    private ArrayTag parseList(ListNBT tag) {
        ArrayTag arrayTag = new ArrayTag();
        for (net.minecraft.nbt.INBT e : tag) {
            arrayTag.getValue().add(parseTag(e));
        }
        return arrayTag;
    }
}
