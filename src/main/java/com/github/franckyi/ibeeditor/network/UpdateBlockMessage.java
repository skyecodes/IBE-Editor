package com.github.franckyi.ibeeditor.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class UpdateBlockMessage implements IMessage {

    private IBlockState blockState;
    private Block block;
    private BlockPos blockPos;

    public UpdateBlockMessage() {
    }

    public UpdateBlockMessage(IBlockState blockState, BlockPos blockPos) {
        this.blockState = blockState;
        this.block = blockState.getBlock();
        this.blockPos = blockPos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.blockPos = BlockPos.fromLong(buf.readLong());
        this.block = ByteBufUtils.readRegistryEntry(buf, ForgeRegistries.BLOCKS);
        this.blockState = block.getStateFromMeta(buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
        ByteBufUtils.writeRegistryEntry(buf, block);
        buf.writeInt(block.getMetaFromState(blockState));
    }

    public static class UpdateBlockMessageHandler implements ICustomMessageHandler<UpdateBlockMessage> {

        @Override
        public void accept(UpdateBlockMessage msg, MessageContext ctx) {
            ctx.getServerHandler().player.world.setBlockState(msg.blockPos, msg.blockState);
        }
    }

}