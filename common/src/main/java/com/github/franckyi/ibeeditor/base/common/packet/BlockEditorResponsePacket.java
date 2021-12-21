package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.ibeeditor.base.common.EditorType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.state.BlockState;

import java.io.IOException;

public class BlockEditorResponsePacket extends BlockEditorRequestPacket {
    private final BlockState state;
    private final CompoundTag tag;

    public BlockEditorResponsePacket(EditorType editorType, BlockPos pos, BlockState state, CompoundTag tag) {
        super(editorType, pos);
        this.state = state;
        this.tag = tag;
    }

    public BlockEditorResponsePacket(FriendlyByteBuf buffer) {
        super(buffer);
        state = buffer.readWithCodec(BlockState.CODEC);
        tag = buffer.readNbt();
    }

    @Override
    public void write(FriendlyByteBuf buffer) throws IOException {
        super.write(buffer);
        buffer.writeWithCodec(BlockState.CODEC, state);
        buffer.writeNbt(tag);
    }

    public BlockState getState() {
        return state;
    }

    public CompoundTag getTag() {
        return tag;
    }
}
