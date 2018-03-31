package com.github.franckyi.ibeeditor.util.item;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.items.CapabilityItemHandler;

import static com.github.franckyi.ibeeditor.IBEEditor.logger;

public abstract class CapabilityBasedItemHandler extends BaseItemHandler {

    private int slotId;

    protected CapabilityBasedItemHandler() {
    }

    protected CapabilityBasedItemHandler(ItemStack itemStack, int slotId) {
        super(itemStack);
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
    public final void updateItemStack(NetHandlerPlayServer server) {
        ICapabilitySerializable<?> c = getCapabilitySerializable(server);
        if (c != null && c.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
                && c.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null) != null) {
            c.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).extractItem(slotId, Integer.MAX_VALUE, false);
            c.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).insertItem(slotId, getItemStack(), false);
            //if (c instanceof TileEntity) ((TileEntity) c).markDirty();
            //if (c instanceof Entity) ((Entity) c).onUpdate();
        } else {
            logger.warn("Couldn't insert the item in the container. Inserting in player's inventory instead.");
            server.player.inventory.addItemStackToInventory(getItemStack());
        }
    }

    protected abstract ICapabilitySerializable<?> getCapabilitySerializable(NetHandlerPlayServer server);
}
