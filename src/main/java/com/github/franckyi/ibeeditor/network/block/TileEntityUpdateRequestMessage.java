package com.github.franckyi.ibeeditor.network.block;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class TileEntityUpdateRequestMessage implements IMessage {

    private BlockPos blockPos;

    public TileEntityUpdateRequestMessage() {
    }

    public TileEntityUpdateRequestMessage(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        blockPos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
    }

    public static class TileEntityUpdateRequestMessageHandler implements IMessageHandler<TileEntityUpdateRequestMessage, TileEntityUpdateReplyMessage> {

        @Override
        public TileEntityUpdateReplyMessage onMessage(TileEntityUpdateRequestMessage message, MessageContext ctx) {
            return new TileEntityUpdateReplyMessage(ctx.getServerHandler().player.world.getTileEntity(message.blockPos));
        }
    }
}
