package com.github.franckyi.gameadapter.forge.mixin.common;

import com.github.franckyi.gameadapter.api.common.IPacketBuffer;
import com.github.franckyi.gameadapter.api.common.tag.ICompoundTag;
import com.github.franckyi.gameadapter.api.common.world.IBlockPos;
import com.github.franckyi.gameadapter.api.common.world.IBlockState;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;

@Mixin(FriendlyByteBuf.class)
public abstract class ForgePacketBufferMixin extends ByteBuf implements IPacketBuffer {
    @Shadow
    @Nullable
    public abstract CompoundTag readNbt();

    @Shadow
    public abstract FriendlyByteBuf writeNbt(@Nullable CompoundTag p_150786_1_);

    @Shadow
    public abstract BlockPos readBlockPos();

    @Shadow
    public abstract FriendlyByteBuf writeBlockPos(BlockPos p_179255_1_);

    @Override
    public ICompoundTag readTag() {
        return (ICompoundTag) readNbt();
    }

    @Override
    public void writeTag(ICompoundTag tag) {
        if (tag != null) {
            writeNbt((CompoundTag) tag);
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
        return (IBlockState) NbtUtils.readBlockState(readNbt());
    }

    @Override
    public void writeBlockState(IBlockState blockState) {
        writeNbt(NbtUtils.writeBlockState((BlockState) blockState));
    }
}
