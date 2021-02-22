package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.Pos;
import com.github.franckyi.gamehooks.api.common.network.Buffer;
import com.github.franckyi.gamehooks.util.common.tag.ObjectTag;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;

public class FabricBuffer implements Buffer<PacketByteBuf> {
    private final PacketByteBuf buf;

    public FabricBuffer() {
        this(PacketByteBufs.create());
    }

    public FabricBuffer(PacketByteBuf buf) {
        this.buf = buf;
    }

    @Override
    public PacketByteBuf getBuffer() {
        return buf;
    }

    @Override
    public ObjectTag readTag() {
        return FabricTagFactory.INSTANCE.parseCompound(buf.readCompoundTag());
    }

    @Override
    public void writeTag(ObjectTag tag) {
        buf.writeCompoundTag(FabricTagFactory.INSTANCE.parseObject(tag));
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
    public Pos readBlockPos() {
        return new FabricPos(buf.readBlockPos());
    }

    @Override
    public void writeBlockPos(Pos pos) {
        buf.writeBlockPos(pos.getPos());
    }
}
