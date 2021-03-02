package com.github.franckyi.gamehooks.impl.common;

import com.github.franckyi.gamehooks.api.common.BlockPos;
import com.github.franckyi.gamehooks.api.common.WorldBlock;
import com.github.franckyi.gamehooks.api.common.tag.CompoundTag;
import com.github.franckyi.gamehooks.impl.common.tag.ForgeTagFactory;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;

public class ForgeWorldBlock implements WorldBlock {
    private final BlockPos blockPos;
    private final BlockState state;
    private final TileEntity tileEntity;

    public ForgeWorldBlock(BlockPos blockPos, BlockState state, TileEntity tileEntity) {
        this.blockPos = blockPos;
        this.state = state;
        this.tileEntity = tileEntity;
    }

    @Override
    public BlockPos getBlockPos() {
        return blockPos;
    }

    @Override
    public CompoundTag getState() {
        return state == null ? null : ForgeTagFactory.parseCompound(NBTUtil.writeBlockState(state));
    }

    @Override
    public CompoundTag getData() {
        return tileEntity == null ? null : ForgeTagFactory.parseCompound(tileEntity.write(new CompoundNBT()));
    }
}
