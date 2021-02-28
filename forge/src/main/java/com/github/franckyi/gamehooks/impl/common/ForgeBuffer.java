package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.BlockPos;
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
        return ForgeTagFactory.parseCompound(buf.readCompoundTag());
    }

    @Override
    public void writeTag(ObjectTag tag) {
        buf.writeCompoundTag(ForgeTagFactory.parseObject(tag));
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
    public BlockPos readPos() {
        return new ForgeBlockPos(buf.readBlockPos());
    }

    @Override
    public void writePos(BlockPos blockPos) {
        buf.writeBlockPos(blockPos.get());
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
