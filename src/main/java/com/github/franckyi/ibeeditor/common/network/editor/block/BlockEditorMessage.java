package com.github.franckyi.ibeeditor.common.network.editor.block;

import com.github.franckyi.ibeeditor.common.network.IMessage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.network.NetworkEvent;

public class BlockEditorMessage implements IMessage {

    protected final BlockPos blockPos;
    protected final IBlockState blockState;
    protected final NBTTagCompound tag;

    public BlockEditorMessage(BlockPos blockPos, IBlockState blockState, TileEntity tileEntity) {
        this.blockPos = blockPos;
        this.blockState = blockState;
        NBTTagCompound tag = new NBTTagCompound();
        this.tag = tileEntity == null ? tag : tileEntity.write(tag);
    }

    public BlockEditorMessage(PacketBuffer buffer) {
        this.blockPos = buffer.readBlockPos();
        this.blockState = NBTUtil.readBlockState(buffer.readCompoundTag());
        this.tag = buffer.readCompoundTag();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeBlockPos(blockPos);
        buffer.writeCompoundTag(NBTUtil.writeBlockState(blockState));
        buffer.writeCompoundTag(tag);
    }

    @Override
    public void handle(NetworkEvent.Context ctx) {
        WorldServer world = ctx.getSender().getServerWorld();
        world.setBlockState(blockPos, blockState);
        TileEntity te = world.getTileEntity(blockPos);
        if (te != null && !tag.isEmpty()) {
            te.read(tag);
            te.markDirty();
        }
    }
}
