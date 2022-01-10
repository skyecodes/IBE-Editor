package com.github.franckyi.ibeeditor.common.packet;

import com.github.franckyi.ibeeditor.common.EditorType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.io.IOException;

public class BlockEditorResponsePacket extends BlockEditorRequestPacket {
    private final BlockState state;
    private final BlockEntity entity;

    public BlockEditorResponsePacket(EditorType editorType, BlockPos pos, BlockState state, BlockEntity entity) {
        super(editorType, pos);
        this.state = state;
        this.entity = entity;
    }

    public BlockEditorResponsePacket(FriendlyByteBuf buffer) {
        super(buffer);
        state = buffer.readWithCodec(BlockState.CODEC);
        var nbt = buffer.readNbt();
        entity = nbt == null ? null : BlockEntity.loadStatic(getPos(), state, nbt);
    }

    @Override
    public void write(FriendlyByteBuf buffer) throws IOException {
        super.write(buffer);
        buffer.writeWithCodec(BlockState.CODEC, state);
        buffer.writeNbt(entity == null ? null : entity.saveWithId());
    }

    public BlockState getState() {
        return state;
    }

    public BlockEntity getEntity() {
        return entity;
    }
}
