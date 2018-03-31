package com.github.franckyi.ibeeditor.util.item;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class EntityInventoryItemHandler extends CapabilityBasedItemHandler {

    private int entityId;

    protected EntityInventoryItemHandler() {
    }

    public EntityInventoryItemHandler(ItemStack itemStack, int slotId, int entityId) {
        super(itemStack, slotId);
        this.entityId = entityId;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);
        entityId = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);
        buf.writeInt(entityId);
    }

    @Override
    protected ICapabilitySerializable<?> getCapabilitySerializable(NetHandlerPlayServer server) {
        return server.player.world.getEntityByID(entityId);
    }
}
