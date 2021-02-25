package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Pos;
import com.github.franckyi.gamehooks.api.common.network.Buffer;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.minecraft.network.PacketBuffer;

public class ForgeBuffer implements Buffer {
    private final PacketBuffer buf;

    public ForgeBuffer(PacketBuffer buf) {
        this.buf = buf;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PacketBuffer getBuffer() {
        return buf;
    }

    @Override
    public ObjectTag readTag() {
        return ForgeTagFactory.INSTANCE.parseCompound(buf.readCompoundTag());
    }

    @Override
    public void writeTag(ObjectTag tag) {
        buf.writeCompoundTag(ForgeTagFactory.INSTANCE.parseObject(tag));
    }

    @Override
    public int readInt() {
        return buf.readInt();
    }

    @Override
    public void writeInt(int i) {
        buf.writeInt(i);
    }

    @Override
    public Pos readPos() {
        return new ForgePos(buf.readBlockPos());
    }

    @Override
    public void writePos(Pos pos) {
        buf.writeBlockPos(pos.getPos());
    }

    @Override
    public boolean readBoolean() {
        return buf.readBoolean();
    }

    @Override
    public void writeBoolean(boolean bl) {
        buf.writeBoolean(bl);
    }

    @Override
    public byte readByte() {
        return buf.readByte();
    }

    @Override
    public void writeByte(byte b) {
        buf.writeByte(b);
    }
}
