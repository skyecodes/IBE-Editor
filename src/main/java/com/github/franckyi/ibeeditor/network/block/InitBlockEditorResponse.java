package com.github.franckyi.ibeeditor.network.block;

import com.github.franckyi.ibeeditor.client.block.BlockEditor;
import com.github.franckyi.ibeeditor.network.IMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

public class InitBlockEditorResponse implements IMessage {

    protected NBTTagCompound tag;
    protected BlockPos blockPos;

    public InitBlockEditorResponse(TileEntity tileEntity) {
        this(tileEntity.getPos());
        this.tag = tileEntity.write(new NBTTagCompound());
    }

    public InitBlockEditorResponse(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public InitBlockEditorResponse(PacketBuffer buffer) {
        this.blockPos = buffer.readBlockPos();
        if (buffer.readBoolean()) {
            this.tag = buffer.readCompoundTag();
        }
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeBlockPos(blockPos);
        buffer.writeBoolean(tag != null);
        if (tag != null) {
            buffer.writeCompoundTag(tag);
        }
    }

    @Override
    public void handle(NetworkEvent.Context context) {
        TileEntity tileEntity = Minecraft.getInstance().world.getTileEntity(blockPos);
        if (tileEntity != null && tag != null) {
            tileEntity.read(tag);
        }
        new BlockEditor(blockPos, tileEntity);
    }
}
