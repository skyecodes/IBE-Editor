package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.gameadapter.api.common.BlockData;
import com.github.franckyi.gameadapter.api.common.IPacketBuffer;

public class BlockEditorResponsePacket extends BlockEditorRequestPacket {
    private final BlockData block;

    public BlockEditorResponsePacket(BlockEditorRequestPacket request, BlockData block) {
        super(request.getPos(), request.getType());
        this.block = block;
    }

    public BlockEditorResponsePacket(IPacketBuffer buffer) {
        super(buffer);
        block = new BlockData(buffer.readBlockState(), buffer.readTag());
    }

    @Override
    public void write(IPacketBuffer buffer) {
        super.write(buffer);
        buffer.writeBlockState(block.getState());
        buffer.writeTag(block.getTag());
    }

    public BlockData getBlock() {
        return block;
    }
}
