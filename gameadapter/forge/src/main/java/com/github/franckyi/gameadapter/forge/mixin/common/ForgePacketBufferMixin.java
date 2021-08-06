package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.IBlockPos;
import com.github.franckyi.gameadapter.api.common.IBlockState;
import com.github.franckyi.gameadapter.api.common.IPacketBuffer;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;

@Mixin(PacketBuffer.class)
public abstract class ForgePacketBufferMixin extends ByteBuf implements IPacketBuffer {
    @Shadow
    @Nullable
    public abstract CompoundNBT readNbt();

    @Shadow
    public abstract PacketBuffer writeNbt(@Nullable CompoundNBT p_150786_1_);

    @Shadow
    public abstract BlockPos readBlockPos();

    @Shadow
    public abstract PacketBuffer writeBlockPos(BlockPos p_179255_1_);

    @Override
    public ICompoundTag readTag() {
        return (ICompoundTag) readNbt();
    }

    @Override
    public void writeTag(ICompoundTag tag) {
        if (tag != null) {
            writeNbt((CompoundNBT) tag);
        } else {
            writeNbt(null);
        }
    }

    @Override
    public IBlockPos readPos() {
        return (IBlockPos) readBlockPos();
    }

    @Override
    public void writePos(IBlockPos blockPos) {
        writeBlockPos((BlockPos) blockPos);
    }

    @Override
    public IBlockState readBlockState() {
        return (IBlockState) NBTUtil.readBlockState(readNbt());
    }

    @Override
    public void writeBlockState(IBlockState blockState) {
        writeNbt(NBTUtil.writeBlockState((BlockState) blockState));
    }
}
