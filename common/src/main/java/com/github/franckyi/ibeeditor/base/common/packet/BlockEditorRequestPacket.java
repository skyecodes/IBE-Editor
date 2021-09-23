package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.ibeeditor.base.common.EditorType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

import java.io.IOException;

public class BlockEditorRequestPacket extends AbstractEditorRequestPacket {
    private final BlockPos pos;

    public BlockEditorRequestPacket(EditorType editorType, BlockPos pos) {
        super(editorType);
        this.pos = pos;
    }

    public BlockEditorRequestPacket(FriendlyByteBuf buffer) {
        super(buffer);
        pos = buffer.readBlockPos();
    }

    @Override
    public void write(FriendlyByteBuf buffer) throws IOException {
        super.write(buffer);
        buffer.writeBlockPos(pos);
    }

    public BlockPos getPos() {
        return pos;
    }
}
