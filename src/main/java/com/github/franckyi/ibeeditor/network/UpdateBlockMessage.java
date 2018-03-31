package com.github.franckyi.ibeeditor.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UpdateBlockMessage implements IMessage {

    private IBlockState blockState;
    private BlockPos blockPos;

    public UpdateBlockMessage() {
    }

    public UpdateBlockMessage(IBlockState blockState, BlockPos blockPos) {
        this.blockState = blockState;
        this.blockPos = blockPos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.blockPos = BlockPos.fromLong(buf.readLong());
        NBTTagCompound c = ByteBufUtils.readTag(buf);
        this.blockState = NBTUtil.readBlockState(c);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
        NBTTagCompound c = NBTUtil.writeBlockState(new NBTTagCompound(), blockState);
        ByteBufUtils.writeTag(buf, c);
    }

    public static class UpdateBlockMessageHandler implements ICustomMessageHandler<UpdateBlockMessage> {

        @Override
        public void accept(UpdateBlockMessage msg, MessageContext ctx) {
            ctx.getServerHandler().player.world.setBlockState(msg.blockPos, msg.blockState);
        }
    }

}