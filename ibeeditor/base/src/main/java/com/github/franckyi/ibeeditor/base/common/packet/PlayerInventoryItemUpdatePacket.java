package com.github.franckyi.ibeeditor.base.common.packet;

import com.github.franckyi.gameadapter.api.common.IItemStack;
import com.github.franckyi.gameadapter.api.common.IPacketBuffer;

public class PlayerInventoryItemUpdatePacket extends PlayerMainHandItemUpdatePacket {
    private final int slotId;

    public PlayerInventoryItemUpdatePacket(IItemStack itemStack, int slotId) {
        super(itemStack);
        this.slotId = slotId;
    }

    public PlayerInventoryItemUpdatePacket(IPacketBuffer buffer) {
        super(buffer);
        this.slotId = buffer.readInt();
    }

    @Override
    public void write(IPacketBuffer buffer) {
        super.write(buffer);
        buffer.writeInt(slotId);
    }

    public int getSlotId() {
        return slotId;
    }
}
