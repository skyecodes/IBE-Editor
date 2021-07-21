package com.github.franckyi.gameadapter.forge.common.network;

import com.github.franckyi.gameadapter.api.common.BlockPos;
import com.github.franckyi.gameadapter.api.common.network.Buffer;
import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.forge.common.ForgeBlockPos;
import com.github.franckyi.gameadapter.forge.common.nbt.ForgeTagFactory;
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
    public CompoundTag readTag() {
        return ForgeTagFactory.parseCompound(buf.readNbt());
    }

    @Override
    public void writeTag(CompoundTag tag) {
        if (tag != null) {
            buf.writeNbt(tag.get());
        } else {
            buf.writeNbt(null);
        }
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
