package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.minecraft.Minecraft;
import com.github.franckyi.minecraft.api.common.network.Buffer;
import com.github.franckyi.minecraft.api.common.world.Block;

public class OpenBlockEditorResponsePacket extends OpenBlockEditorRequestPacket {
    private final Block block;

    public OpenBlockEditorResponsePacket(OpenBlockEditorRequestPacket request, Block block) {
        super(request.getPos(), request.isNBT());
        this.block = block;
    }

    public OpenBlockEditorResponsePacket(Buffer buffer) {
        super(buffer);
        block = Minecraft.getCommon().createBlock(buffer.readTag(), buffer.readTag());
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
