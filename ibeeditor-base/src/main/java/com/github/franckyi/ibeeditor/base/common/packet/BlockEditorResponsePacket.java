package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.gameadapter.Game;
import com.github.franckyi.gameadapter.api.common.network.Buffer;
import com.github.franckyi.gameadapter.api.common.world.Block;

public class BlockEditorResponsePacket extends BlockEditorRequestPacket {
    private final Block block;

    public BlockEditorResponsePacket(BlockEditorRequestPacket request, Block block) {
        super(request.getPos(), request.getType());
        this.block = block;
    }

    public BlockEditorResponsePacket(Buffer buffer) {
        super(buffer);
        block = Game.getCommon().createBlock(buffer.readTag(), buffer.readTag());
    }

    @Override
    public void write(Buffer buffer) {
        super.write(buffer);
        buffer.writeTag(block.getState());
        buffer.writeTag(block.getData());
    }

    public Block getBlock() {
        return block;
    }
}
