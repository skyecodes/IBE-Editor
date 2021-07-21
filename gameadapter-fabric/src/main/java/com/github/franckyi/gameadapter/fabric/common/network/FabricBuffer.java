package com.github.franckyi.gameadapter.fabric.common.network;

import com.github.franckyi.gameadapter.api.common.BlockPos;
import com.github.franckyi.gameadapter.api.common.network.Buffer;
import com.github.franckyi.gameadapter.api.common.tag.CompoundTag;
import com.github.franckyi.gameadapter.fabric.common.FabricBlockPos;
import com.github.franckyi.gameadapter.fabric.common.nbt.FabricTagFactory;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;

public class FabricBuffer implements Buffer {
    private final PacketByteBuf buf;

    public FabricBuffer() {
        this(PacketByteBufs.create());
    }

    public FabricBuffer(PacketByteBuf buf) {
        this.buf = buf;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PacketByteBuf getBuffer() {
        return buf;
    }

    @Override
    public CompoundTag readTag() {
        return FabricTagFactory.parseCompound(buf.readNbt());
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
        return new FabricBlockPos(buf.readBlockPos());
    }

    @Override
    public void writePos(BlockPos blockPos) {
        if (blockPos != null) {
            buf.writeBlockPos(blockPos.get());
        }
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
