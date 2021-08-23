package com.github.franckyi.gameadapter.fabric.mixin.common;

import com.github.franckyi.gameadapter.api.common.IPacketBuffer;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.world.IBlockPos;
import com.github.franckyi.gameadapter.api.common.world.IBlockState;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PacketByteBuf.class)
public abstract class FabricPacketByteBufMixin extends ByteBuf implements IPacketBuffer {
    @Shadow
    @Nullable
    public abstract NbtCompound readNbt();

    @Shadow
    public abstract PacketByteBuf writeNbt(@Nullable NbtCompound compound);

    @Shadow
    public abstract BlockPos readBlockPos();

    @Shadow
    public abstract PacketByteBuf writeBlockPos(BlockPos pos);

    @Override
    public ICompoundTag readTag() {
        return (ICompoundTag) readNbt();
    }

    @Override
    public void writeTag(ICompoundTag tag) {
        if (tag != null) {
            writeNbt((NbtCompound) tag);
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
        return (IBlockState) NbtHelper.toBlockState(readNbt());
    }

    @Override
    public void writeBlockState(IBlockState blockState) {
        writeNbt(NbtHelper.fromBlockState((BlockState) blockState));
    }
}
