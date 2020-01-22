package com.github.franckyi.ibeeditor.common.network.editor.block;

import com.github.franckyi.ibeeditor.common.network.IMessage;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

public class BlockEditorMessage implements IMessage {

    protected final BlockPos blockPos;
    protected final BlockState blockState;
    protected final CompoundNBT tag;

    public BlockEditorMessage(BlockPos blockPos, BlockState blockState, TileEntity tileEntity) {
        this.blockPos = blockPos;
        this.blockState = blockState;
        CompoundNBT tag = new CompoundNBT();
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
        World world = ctx.getSender().getEntityWorld();
        world.setBlockState(blockPos, blockState);
        TileEntity te = world.getTileEntity(blockPos);
        if (te != null && !tag.isEmpty()) {
            te.read(tag);
            te.markDirty();
        }
    }
}
