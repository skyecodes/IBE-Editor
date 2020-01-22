package com.github.franckyi.ibeeditor.common.network.editor.block;

import com.github.franckyi.ibeeditor.common.network.IBENetworkHandler;
import com.github.franckyi.ibeeditor.common.network.IMessage;
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
    public void handle(NetworkEvent.Context ctx) {
        TileEntity tileEntity = ctx.getSender().getEntityWorld().getTileEntity(blockPos);
        if (tileEntity == null) {
            IBENetworkHandler.getModChannel().send(PacketDistributor.PLAYER.with(ctx::getSender),
                    new InitBlockEditorResponse(blockPos));
        } else {
            IBENetworkHandler.getModChannel().send(PacketDistributor.PLAYER.with(ctx::getSender),
                    new InitBlockEditorResponse(tileEntity));
        }
    }
}
