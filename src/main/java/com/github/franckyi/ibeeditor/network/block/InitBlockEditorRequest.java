package com.github.franckyi.ibeeditor.network.block;

import com.github.franckyi.ibeeditor.IBEEditorMod;
import com.github.franckyi.ibeeditor.network.IMessage;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

public class InitBlockEditorRequest implements IMessage {

    protected final BlockPos blockPos;

    public InitBlockEditorRequest(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public InitBlockEditorRequest(PacketBuffer buffer) {
        this.blockPos = buffer.readBlockPos();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeBlockPos(blockPos);
    }

    @Override
    public void handle(NetworkEvent.Context context) {
        TileEntity tileEntity = context.getSender().getServerWorld().getTileEntity(blockPos);
        if (tileEntity == null) {
            IBEEditorMod.CHANNEL.send(PacketDistributor.PLAYER.with(context::getSender),
                    new InitBlockEditorResponse(blockPos));
        } else {
            IBEEditorMod.CHANNEL.send(PacketDistributor.PLAYER.with(context::getSender),
                    new InitBlockEditorResponse(tileEntity));
        }
    }
}
