package com.github.franckyi.ibeeditor.common.packet;

import com.github.franckyi.ibeeditor.common.Packet;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.state.BlockState;

import java.io.IOException;

public class BlockUpdatePacket implements Packet {
    private final BlockPos pos;
    private final BlockState state;
    private final CompoundTag tag;

    public BlockUpdatePacket(BlockPos pos, BlockState state, CompoundTag tag) {
        this.pos = pos;
        this.state = state;
        this.tag = tag;
    }

    public BlockUpdatePacket(FriendlyByteBuf buffer) throws IOException {
        this(buffer.readBlockPos(), buffer.readWithCodec(BlockState.CODEC), buffer.readNbt());
    }

    @Override
    public void write(FriendlyByteBuf buffer) throws IOException {
        buffer.writeBlockPos(pos);
        buffer.writeWithCodec(BlockState.CODEC, state);
        buffer.writeNbt(tag);
    }

    public BlockPos getPos() {
        return pos;
    }

    public BlockState getState() {
        return state;
    }

    public CompoundTag getTag() {
        return tag;
    }
}
