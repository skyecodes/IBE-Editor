package com.github.franckyi.ibeeditor.common.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

public class PlayerInventoryItemUpdatePacket extends PlayerMainHandItemUpdatePacket {
    private final int slotId;

    public PlayerInventoryItemUpdatePacket(ItemStack itemStack, int slotId) {
        super(itemStack);
        this.slotId = slotId;
    }

    public PlayerInventoryItemUpdatePacket(FriendlyByteBuf buffer) {
        super(buffer);
        this.slotId = buffer.readInt();
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        super.write(buffer);
        buffer.writeInt(slotId);
    }

    public int getSlotId() {
        return slotId;
    }
}
