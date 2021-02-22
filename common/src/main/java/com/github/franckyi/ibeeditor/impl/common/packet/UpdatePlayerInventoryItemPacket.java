package com.github.franckyi.ibeeditor.impl.common.packet;

import com.github.franckyi.gamehooks.api.common.Item;
import com.github.franckyi.gamehooks.api.common.network.Buffer;

public class UpdatePlayerInventoryItemPacket extends UpdatePlayerMainHandItemPacket {
    private final int slotId;

    public UpdatePlayerInventoryItemPacket(Item item, int slotId) {
        super(item);
        this.slotId = slotId;
    }

    public UpdatePlayerInventoryItemPacket(Buffer buffer) {
        super(buffer);
        this.slotId = buffer.readInt();
    }

    @Override
    public void write(Buffer buffer) {
        super.write(buffer);
        buffer.writeInt(slotId);
    }

    public int getSlotId() {
        return slotId;
    }
}
