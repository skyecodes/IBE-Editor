package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.gamehooks.GameHooks;
import com.github.franckyi.gamehooks.api.common.Block;
import com.github.franckyi.gamehooks.api.common.Pos;
import com.github.franckyi.gamehooks.api.common.network.Buffer;
import com.github.franckyi.gamehooks.api.common.network.Packet;

public class UpdateBlockPacket implements Packet {
    private final Pos pos;
    private final Block block;

    public UpdateBlockPacket(Pos pos, Block block) {
        this.pos = pos;
        this.block = block;
    }

    public UpdateBlockPacket(Buffer buffer) {
        this(buffer.readPos(), GameHooks.common().createBlockFromTag(buffer.readTag()));
    }

    @Override
    public void write(Buffer buffer) {
        buffer.writePos(pos);
        buffer.writeTag(block.getTag());
    }

    public Pos getPos() {
        return pos;
    }

    public Block getBlock() {
        return block;
    }
}
