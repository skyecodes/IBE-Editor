package com.github.franckyi.ibeeditor.util.item;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.FMLNetworkException;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public abstract class BaseItemHandler implements IMessage {

    private ItemStack itemStack;

    protected BaseItemHandler() {
    }

    public BaseItemHandler(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public static BaseItemHandler read(ByteBuf buf) {
        int flag = buf.readByte();
        BaseItemHandler itemHandler;
        switch (flag) {
            case 0:
                itemHandler = new HeldItemHandler();
                break;
            case 1:
                itemHandler = new PlayerInventoryItemHandler();
                break;
            case 2:
                itemHandler = new TileEntityInventoryItemHandler();
                break;
            case 3:
                itemHandler = new EntityInventoryItemHandler();
                break;
            default:
                throw new FMLNetworkException("Couldn't read item editor update message.");
        }
        itemHandler.fromBytes(buf);
        return itemHandler;
    }

    public final ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public abstract void updateItemStack(NetHandlerPlayServer server);

    @Override
    public void fromBytes(ByteBuf buf) {
        itemStack = ByteBufUtils.readItemStack(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        if (this instanceof HeldItemHandler) {
            buf.writeByte(0);
        } else if (this instanceof PlayerInventoryItemHandler) {
            buf.writeByte(1);
        } else if (this instanceof TileEntityInventoryItemHandler) {
            buf.writeByte(2);
        } else if (this instanceof EntityInventoryItemHandler) {
            buf.writeByte(3);
        }
        ByteBufUtils.writeItemStack(buf, itemStack);
    }
}
