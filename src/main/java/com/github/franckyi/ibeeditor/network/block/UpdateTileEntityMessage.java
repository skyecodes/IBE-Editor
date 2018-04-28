package com.github.franckyi.ibeeditor.network.block;

import com.github.franckyi.ibeeditor.network.ICustomMessageHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UpdateTileEntityMessage implements IMessage {

    private BlockPos blockPos;
    private int energy;

    public UpdateTileEntityMessage() {
    }

    public UpdateTileEntityMessage(BlockPos blockPos, int energy) {
        this.blockPos = blockPos;
        this.energy = energy;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        blockPos = BlockPos.fromLong(buf.readLong());
        energy = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(blockPos.toLong());
        buf.writeInt(energy);
    }

    public static class UpdateTileEntityMessageHandler implements ICustomMessageHandler<UpdateTileEntityMessage> {

        @Override
        public void accept(UpdateTileEntityMessage msg, MessageContext ctx) {
            TileEntity te = ctx.getServerHandler().player.world.getTileEntity(msg.blockPos);
            if (te != null && te.hasCapability(CapabilityEnergy.ENERGY, null)) {
                IEnergyStorage storage = te.getCapability(CapabilityEnergy.ENERGY, null);
                if (storage.getEnergyStored() < msg.energy) {
                    storage.receiveEnergy(msg.energy - storage.getEnergyStored(), false);
                } else {
                    storage.extractEnergy(storage.getEnergyStored() - msg.energy, false);
                }
            }
        }
    }
}
