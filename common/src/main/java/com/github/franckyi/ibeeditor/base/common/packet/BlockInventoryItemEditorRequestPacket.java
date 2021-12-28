package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.ibeeditor.base.common.EditorType;
import com.github.franckyi.ibeeditor.base.common.Packet;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

import java.io.IOException;

public class BlockInventoryItemEditorRequestPacket extends AbstractEditorRequestPacket {
    private final int slotIndex;
    private final BlockPos pos;

    public BlockInventoryItemEditorRequestPacket(EditorType type, int slotIndex, BlockPos pos) {
        super(type);
        this.slotIndex = slotIndex;
        this.pos = pos;
    }

    public BlockInventoryItemEditorRequestPacket(FriendlyByteBuf buffer) {
        super(buffer);
        slotIndex = buffer.readInt();
        pos = buffer.readBlockPos();
    }

    @Override
    public void write(FriendlyByteBuf buffer) throws IOException {
        super.write(buffer);
        buffer.writeInt(slotIndex);
        buffer.writeBlockPos(pos);
    }

    public int getSlotIndex() {
        return slotIndex;
    }

    public BlockPos getPos() {
        return pos;
    }
}
