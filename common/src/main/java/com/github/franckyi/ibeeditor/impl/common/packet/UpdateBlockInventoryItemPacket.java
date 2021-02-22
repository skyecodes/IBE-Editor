package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.Pos;
import com.github.franckyi.gamehooks.api.common.network.Buffer;

public class UpdateBlockInventoryItemPacket extends UpdatePlayerInventoryItemPacket {
    private final Pos pos;

    public UpdateBlockInventoryItemPacket(Item item, int slotId, Pos pos) {
        super(item, slotId);
        this.pos = pos;
    }

    public UpdateBlockInventoryItemPacket(Buffer<?> buffer) {
        super(buffer);
        pos = buffer.readBlockPos();
    }

    @Override
    public void write(Buffer<?> buffer) {
        super.write(buffer);
        buffer.writeBlockPos(pos);
    }

    public Pos getPos() {
        return pos;
    }
}
