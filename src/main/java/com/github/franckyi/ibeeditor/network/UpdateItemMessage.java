package com.github.franckyi.ibeeditor.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.items.CapabilityItemHandler;

import static com.github.franckyi.ibeeditor.IBEEditor.logger;

public class UpdateItemMessage implements IMessage {

    private ItemStack itemStack = ItemStack.EMPTY;
    private int slotId = -1;
    private BlockPos blockPos;

    private boolean flag;

    public UpdateItemMessage() {
    }

    public UpdateItemMessage(ItemStack itemStack, int slotId, BlockPos blockPos) {
        this.itemStack = itemStack;
        this.slotId = slotId;
        this.blockPos = blockPos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        itemStack = ByteBufUtils.readItemStack(buf);
        slotId = buf.readInt();
        flag = buf.readBoolean();
        if(flag) blockPos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        flag = blockPos != null;
        ByteBufUtils.writeItemStack(buf, itemStack);
        buf.writeInt(slotId);
        buf.writeBoolean(flag);
        if(flag) buf.writeLong(blockPos.toLong());
    }

    public static class UpdateItemMessageHandler implements ICustomMessageHandler<UpdateItemMessage> {

        @Override
        public void accept(UpdateItemMessage message, MessageContext ctx) {
            if(message.blockPos == null) {
                if (message.slotId < 0) {
                    ctx.getServerHandler().player.inventory.addItemStackToInventory(message.itemStack);
                } else {
                    ctx.getServerHandler().player.inventory.setInventorySlotContents(message.slotId, message.itemStack);
                }
            } else {
                TileEntity te = ctx.getServerHandler().player.world.getTileEntity(message.blockPos);
                if(te != null && te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
                        && te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null) != null) {
                    te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).insertItem(message.slotId, message.itemStack, false);
                } else {
                    logger.warn("Couldn't insert the item in the TileEntity at pos [x={};y={};z={}]. Injecting in player's inventory instead.",
                            message.blockPos.getX(), message.blockPos.getY(), message.blockPos.getZ());
                    ctx.getServerHandler().player.inventory.addItemStackToInventory(message.itemStack);
                }
            }
        }
    }

}
