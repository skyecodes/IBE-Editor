package com.github.franckyi.ibeeditor.util.item;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.NetHandlerPlayServer;

public class PlayerInventoryItemHandler extends BaseItemHandler {

    private int slotId;

    protected PlayerInventoryItemHandler() {
    }

    public PlayerInventoryItemHandler(int slotId) {
        super(Minecraft.getMinecraft().player.inventory.getStackInSlot(slotId));
        this.slotId = slotId;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);
        slotId = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);
        buf.writeInt(slotId);
    }

    @Override
    public void updateItemStack(NetHandlerPlayServer server) {
        server.player.inventory.setInventorySlotContents(slotId, getItemStack());
    }
}
