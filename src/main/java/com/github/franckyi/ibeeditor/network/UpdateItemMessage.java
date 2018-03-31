package com.github.franckyi.ibeeditor.network;

import com.github.franckyi.ibeeditor.util.item.BaseItemHandler;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UpdateItemMessage implements IMessage {

    private BaseItemHandler itemHandler;

    public UpdateItemMessage() {
    }

    public UpdateItemMessage(BaseItemHandler itemHandler) {
        this.itemHandler = itemHandler;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        itemHandler = BaseItemHandler.read(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        itemHandler.toBytes(buf);
    }

    public static class UpdateItemMessageHandler implements ICustomMessageHandler<UpdateItemMessage> {

        @Override
        public void accept(UpdateItemMessage message, MessageContext ctx) {
            message.itemHandler.updateItemStack(ctx.getServerHandler());
        }
    }

}
